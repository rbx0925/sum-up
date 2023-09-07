package com.ikang.idata.search.search;

import com.alibaba.csp.sentinel.Constants;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;

public final class ContextTestUtil {

    public static void cleanUpContext() {
        Context context = ContextUtil.getContext();
        if (context != null) {
            context.setCurEntry(null);
            ContextUtil.exit();
        }
    }

    public static void resetContextMap() {
//        ContextUtil.resetContextMap();
        Constants.ROOT.removeChildList();
    }

    private ContextTestUtil() {}
}