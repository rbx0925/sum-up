package com.ikang.idata.search.search.javaTest;

import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.ConstructingObjectParser;
import org.elasticsearch.common.xcontent.ObjectParser;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregatorFactory;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.AbstractPipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.BucketHelpers;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregator;
import org.elasticsearch.search.aggregations.pipeline.bucketsort.BucketSortPipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.bucketsort.BucketSortPipelineAggregator;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.io.IOException;
import java.util.*;

import static org.elasticsearch.common.xcontent.ConstructingObjectParser.optionalConstructorArg;
import static org.elasticsearch.search.aggregations.pipeline.PipelineAggregator.Parser.GAP_POLICY;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/21
 */
public class BucketSortPipelineAggregationBuilderTest extends AbstractPipelineAggregationBuilder<BucketSortPipelineAggregationBuilder> {
    public static final String NAME = "bucket_sort";

    private static final ParseField FROM = new ParseField("from");
    private static final ParseField SIZE = new ParseField("size");

    public static final ConstructingObjectParser<BucketSortPipelineAggregationBuilder, String> PARSER = new ConstructingObjectParser<>(NAME,
            false, (a, context) -> new BucketSortPipelineAggregationBuilder(context, (List<FieldSortBuilder>) a[0]));

    static {
        PARSER.declareField(optionalConstructorArg(), (p, c) -> {
                    List<SortBuilder<?>> sorts = SortBuilder.fromXContent(p);
                    List<FieldSortBuilder> fieldSorts = new ArrayList<>(sorts.size());
                    for (SortBuilder<?> sort : sorts) {
                        if (sort instanceof FieldSortBuilder == false) {
                            throw new IllegalArgumentException("[" + NAME + "] only supports field based sorting; incompatible sort: ["
                                    + sort + "]");
                        }
                        fieldSorts.add((FieldSortBuilder) sort);
                    }
                    return fieldSorts;
                }, SearchSourceBuilder.SORT_FIELD,
                ObjectParser.ValueType.OBJECT_ARRAY);
        PARSER.declareInt(BucketSortPipelineAggregationBuilder::from, FROM);
        PARSER.declareInt(BucketSortPipelineAggregationBuilder::size, SIZE);
        PARSER.declareField(BucketSortPipelineAggregationBuilder::gapPolicy, p -> {
            if (p.currentToken() == XContentParser.Token.VALUE_STRING) {
                return BucketHelpers.GapPolicy.parse(p.text().toLowerCase(Locale.ROOT), p.getTokenLocation());
            }
            throw new IllegalArgumentException("Unsupported token [" + p.currentToken() + "]");
        }, GAP_POLICY, ObjectParser.ValueType.STRING);
    }

    private List<FieldSortBuilder> sorts = Collections.emptyList();
    private int from = 0;
    private Integer size;
    private BucketHelpers.GapPolicy gapPolicy = BucketHelpers.GapPolicy.SKIP;

    public BucketSortPipelineAggregationBuilderTest(String name, List<FieldSortBuilder> sorts) {
        super(name, NAME, sorts == null ? new String[0] : sorts.stream().map(s -> s.getFieldName()).toArray(String[]::new));
        this.sorts = sorts == null ? Collections.emptyList() : sorts;
    }

    /**
     * Read from a stream.
     */
    public BucketSortPipelineAggregationBuilderTest(StreamInput in) throws IOException {
        super(in, NAME);
        sorts = in.readList(FieldSortBuilder::new);
        from = in.readVInt();
        size = in.readOptionalVInt();
        gapPolicy = BucketHelpers.GapPolicy.readFrom(in);
    }

    @Override
    protected void doWriteTo(StreamOutput out) throws IOException {
        out.writeList(sorts);
        out.writeVInt(from);
        out.writeOptionalVInt(size);
        gapPolicy.writeTo(out);
    }

    public BucketSortPipelineAggregationBuilderTest from(int from) {
        if (from < 0) {
            throw new IllegalArgumentException("[" + FROM.getPreferredName() + "] must be a non-negative integer: [" + from + "]");
        }
        this.from = from;
        return this;
    }

    public BucketSortPipelineAggregationBuilderTest size(Integer size) {
        if (size != null && size <= 0) {
            throw new IllegalArgumentException("[" + SIZE.getPreferredName() + "] must be a positive integer: [" + size + "]");
        }
        this.size = size;
        return this;
    }

    public BucketSortPipelineAggregationBuilderTest gapPolicy(BucketHelpers.GapPolicy gapPolicy) {
        if (gapPolicy == null) {
            throw new IllegalArgumentException("[" + GAP_POLICY.getPreferredName() + "] must not be null: [" + name + "]");
        }
        this.gapPolicy = gapPolicy;
        return this;
    }

    @Override
    protected PipelineAggregator createInternal(Map<String, Object> metaData) throws IOException {
        return new BucketSortPipelineAggregator(name, sorts, from, size, gapPolicy, metaData);
    }

    @Override
    public void doValidate(AggregatorFactory<?> parent, Collection<AggregationBuilder> aggFactories,
                           Collection<PipelineAggregationBuilder> pipelineAggregatoractories) {
        if (sorts.isEmpty() && size == null && from == 0) {
            throw new IllegalStateException("[" + name + "] is configured to perform nothing. Please set either of "
                    + Arrays.asList(SearchSourceBuilder.SORT_FIELD.getPreferredName(), SIZE.getPreferredName(), FROM.getPreferredName())
                    + " to use " + NAME);
        }
    }

    @Override
    protected XContentBuilder internalXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field(SearchSourceBuilder.SORT_FIELD.getPreferredName(), sorts);
        builder.field(FROM.getPreferredName(), from);
        if (size != null) {
            builder.field(SIZE.getPreferredName(), size);
        }
        builder.field(GAP_POLICY.getPreferredName(), gapPolicy);
        return builder;
    }

    public static BucketSortPipelineAggregationBuilder parse(String reducerName, XContentParser parser) throws IOException {
        return PARSER.parse(parser, reducerName);
    }

    @Override
    protected boolean overrideBucketsPath() {
        return true;
    }

    @Override
    protected int doHashCode() {
        return Objects.hash(sorts, from, size, gapPolicy);
    }

    @Override
    protected boolean doEquals(Object obj) {
        BucketSortPipelineAggregationBuilderTest other = (BucketSortPipelineAggregationBuilderTest) obj;
        return Objects.equals(sorts, other.sorts)
                && Objects.equals(from, other.from)
                && Objects.equals(size, other.size)
                && Objects.equals(gapPolicy, other.gapPolicy);
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }
}

