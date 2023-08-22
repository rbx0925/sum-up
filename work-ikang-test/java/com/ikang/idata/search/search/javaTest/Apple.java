package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.crypto.*;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/26
 */
public class Apple {

    private Integer num;
    private String name;
    private String color;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Apple(Integer num, String name, String color) {
        this.num = num;
        this.name = name;
        this.color = color;
    }

    public Apple() {
    }



    private static final long serialVersionUID = 1L;

    /**
     * 时长毫秒数
     */
    private long betweenMs;
    /**
     * 格式化级别
     */
    private BetweenFormatter.Level level;
    /**
     * 格式化级别的最大个数
     */
    private  int levelMaxCount;

    /**
     * 构造
     *
     * @param betweenMs 日期间隔
     * @param level     级别，按照天、小时、分、秒、毫秒分为5个等级，根据传入等级，格式化到相应级别
     */
    public Apple(long betweenMs, BetweenFormatter.Level level) {
        this(betweenMs, level, 0);
    }

    /**
     * 构造
     *
     * @param betweenMs     日期间隔
     * @param level         级别，按照天、小时、分、秒、毫秒分为5个等级，根据传入等级，格式化到相应级别
     * @param levelMaxCount 格式化级别的最大个数，假如级别个数为1，但是级别到秒，那只显示一个级别
     */
    public Apple(long betweenMs, BetweenFormatter.Level level, int levelMaxCount) {
        this.betweenMs = betweenMs;
        this.level = level;
        this.levelMaxCount = levelMaxCount;
    }

    /**
     * 格式化日期间隔输出<br>
     *
     * @return 格式化后的字符串
     */
    public String format() {
        final StringBuilder sb = new StringBuilder();
//        if (betweenMs > 0) {
//            long day = betweenMs / DateUnit.DAY.getMillis();
//            long hour = betweenMs / DateUnit.HOUR.getMillis() - day * 24;
//            long minute = betweenMs / DateUnit.MINUTE.getMillis() - day * 24 * 60 - hour * 60;
//
//            final long BetweenOfSecond = ((day * 24 + hour) * 60 + minute) * 60;
//            long second = betweenMs / DateUnit.SECOND.getMillis() - BetweenOfSecond;
//            long millisecond = betweenMs - (BetweenOfSecond + second) * 1000;
//
//            final int level = this.level.ordinal();
//            int levelCount = 0;
//
//            if (isLevelCountValid(levelCount) && 0 != day && level >= BetweenFormatter.Level.DAY.ordinal()) {
//                sb.append(day).append(BetweenFormatter.Level.DAY.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != hour && level >= BetweenFormatter.Level.HOUR.ordinal()) {
//                sb.append(hour).append(BetweenFormatter.Level.HOUR.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != minute && level >= BetweenFormatter.Level.MINUTE.ordinal()) {
//                sb.append(minute).append(BetweenFormatter.Level.MINUTE.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != second && level >= BetweenFormatter.Level.SECOND.ordinal()) {
//                sb.append(second).append(BetweenFormatter.Level.SECOND.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != millisecond && level >= BetweenFormatter.Level.MILLISECOND.ordinal()) {
//                sb.append(millisecond).append(BetweenFormatter.Level.MILLISECOND.name);
//                // levelCount++;
//            }
//        }
//
//        if (StrUtil.isEmpty(sb)) {
//            sb.append(0).append(this.level.name);
//        }

        return sb.toString();
    }

    /**
     * 获得 时长毫秒数
     *
     * @return 时长毫秒数
     */
    public long getBetweenMs() {
        return betweenMs;
    }

    /**
     * 设置 时长毫秒数
     *
     * @param betweenMs 时长毫秒数
     */
    public void setBetweenMs(long betweenMs) {
        this.betweenMs = betweenMs;
    }

    /**
     * 获得 格式化级别
     *
     * @return 格式化级别
     */
    public BetweenFormatter.Level getLevel() {
        return level;
    }

    /**
     * 设置格式化级别
     *
     * @param level 格式化级别
     */
    public void setLevel(BetweenFormatter.Level level) {
        this.level = level;
    }

    /**
     * 格式化等级枚举
     *
     * @author Looly
     */
    public enum Level {

        /**
         * 天
         */
        DAY("天"),
        /**
         * 小时
         */
        HOUR("小时"),
        /**
         * 分钟
         */
        MINUTE("分"),
        /**
         * 秒
         */
        SECOND("秒"),
        /**
         * 毫秒
         */
        MILLISECOND("毫秒");

        /**
         * 级别名称
         */
        private final String name;

        /**
         * 构造
         *
         * @param name 级别名称
         */
        Level(String name) {
            this.name = name;
        }

        /**
         * 获取级别名称
         *
         * @return 级别名称
         */
        public String getName() {
            return this.name;
        }
    }

    @Override
    public String toString() {
        return format();
    }

    /**
     * 等级数量是否有效<br>
     * 有效的定义是：levelMaxCount大于0（被设置），当前等级数量没有超过这个最大值
     *
     * @param levelCount 登记数量
     * @return 是否有效
     */
    private boolean isLevelCountValid(int levelCount) {
        return this.levelMaxCount <= 0 || levelCount < this.levelMaxCount;
    }


    /**
     * 只获取私钥里的d，32字节
     *
     * @param privateKey {@link PublicKey}，必须为org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey
     * @return 压缩得到的X
     * @since 5.1.6
     */
    public static byte[] encodeECPrivateKey(PrivateKey privateKey) {
        return ((BCECPrivateKey) privateKey).getD().toByteArray();
    }

    /**
     * 编码压缩EC公钥（基于BouncyCastle），即Q值<br>
     * 见：https://www.cnblogs.com/xinzhao/p/8963724.html
     *
     * @param publicKey {@link PublicKey}，必须为org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey
     * @return 压缩得到的Q
     * @since 4.4.4
     */
    public static byte[] encodeECPublicKey(PublicKey publicKey) {
        return encodeECPublicKey(publicKey, true);
    }

    /**
     * 编码压缩EC公钥（基于BouncyCastle），即Q值<br>
     * 见：https://www.cnblogs.com/xinzhao/p/8963724.html
     *
     * @param publicKey {@link PublicKey}，必须为org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey
     * @param isCompressed 是否压缩
     * @return 得到的Q
     * @since 5.5.9
     */
    public static byte[] encodeECPublicKey(PublicKey publicKey, boolean isCompressed) {
        return ((BCECPublicKey) publicKey).getQ().getEncoded(isCompressed);
    }

    /**
     * 解码恢复EC压缩公钥,支持Base64和Hex编码,（基于BouncyCastle）<br>
     * 见：https://www.cnblogs.com/xinzhao/p/8963724.html
     *
     * @param encode    压缩公钥
     * @param curveName EC曲线名
     * @return 公钥
     * @since 4.4.4
     */
    public static PublicKey decodeECPoint(String encode, String curveName) {
        return decodeECPoint(SecureUtil.decode(encode), curveName);
    }

    /**
     * 解码恢复EC压缩公钥,支持Base64和Hex编码,（基于BouncyCastle）
     *
     * @param encodeByte 压缩公钥
     * @param curveName  EC曲线名，例如{@link SmUtil#SM2_DOMAIN_PARAMS}
     * @return 公钥
     * @since 4.4.4
     */
    public static PublicKey decodeECPoint(byte[] encodeByte, String curveName) {
        final X9ECParameters x9ECParameters = ECUtil.getNamedCurveByName(curveName);
        final ECCurve curve = x9ECParameters.getCurve();
        final ECPoint point = EC5Util.convertPoint(curve.decodePoint(encodeByte));

        // 根据曲线恢复公钥格式
        final ECNamedCurveSpec ecSpec = new ECNamedCurveSpec(curveName, curve, x9ECParameters.getG(), x9ECParameters.getN());
        return KeyUtil.generatePublicKey("EC", new ECPublicKeySpec(point, ecSpec));
    }

    /**
     * 构建ECDomainParameters对象
     *
     * @param parameterSpec ECParameterSpec
     * @return {@link ECDomainParameters}
     * @since 5.2.0
     */
    public static ECDomainParameters toDomainParams(ECParameterSpec parameterSpec) {
        return new ECDomainParameters(
                parameterSpec.getCurve(),
                parameterSpec.getG(),
                parameterSpec.getN(),
                parameterSpec.getH());
    }

    /**
     * 构建ECDomainParameters对象
     *
     * @param curveName Curve名称
     * @return {@link ECDomainParameters}
     * @since 5.2.0
     */
    public static ECDomainParameters toDomainParams(String curveName) {
        return toDomainParams(ECUtil.getNamedCurveByName(curveName));
    }

    /**
     * 构建ECDomainParameters对象
     *
     * @param x9ECParameters {@link X9ECParameters}
     * @return {@link ECDomainParameters}
     * @since 5.2.0
     */
    public static ECDomainParameters toDomainParams(X9ECParameters x9ECParameters) {
        return new ECDomainParameters(
                x9ECParameters.getCurve(),
                x9ECParameters.getG(),
                x9ECParameters.getN(),
                x9ECParameters.getH()
        );
    }

    /**
     * 密钥转换为AsymmetricKeyParameter
     *
     * @param key PrivateKey或者PublicKey
     * @return ECPrivateKeyParameters或者ECPublicKeyParameters
     * @since 5.2.0
     */
    public static AsymmetricKeyParameter toParams(Key key) {
        return ECKeyUtil.toParams(key);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d 私钥d值
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toSm2Params(String d) {
        return ECKeyUtil.toSm2PrivateParams(d);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param dHex             私钥d值16进制字符串
     * @param domainParameters ECDomainParameters
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toParams(String dHex, ECDomainParameters domainParameters) {
        return ECKeyUtil.toPrivateParams(dHex, domainParameters);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d 私钥d值
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toSm2Params(byte[] d) {
        return ECKeyUtil.toSm2PrivateParams(d);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d                私钥d值
     * @param domainParameters ECDomainParameters
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toParams(byte[] d, ECDomainParameters domainParameters) {
        return ECKeyUtil.toPrivateParams(d, domainParameters);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d 私钥d值
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toSm2Params(BigInteger d) {
        return ECKeyUtil.toSm2PrivateParams(d);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d                私钥d值
     * @param domainParameters ECDomainParameters
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toParams(BigInteger d, ECDomainParameters domainParameters) {
        return ECKeyUtil.toPrivateParams(d, domainParameters);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param x                公钥X
     * @param y                公钥Y
     * @param domainParameters ECDomainParameters
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toParams(BigInteger x, BigInteger y, ECDomainParameters domainParameters) {
        return ECKeyUtil.toPublicParams(x, y, domainParameters);
    }

    /**
     * 转换为SM2的ECPublicKeyParameters
     *
     * @param xHex 公钥X
     * @param yHex 公钥Y
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toSm2Params(String xHex, String yHex) {
        return ECKeyUtil.toSm2PublicParams(xHex, yHex);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param xHex             公钥X
     * @param yHex             公钥Y
     * @param domainParameters ECDomainParameters
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toParams(String xHex, String yHex, ECDomainParameters domainParameters) {
        return ECKeyUtil.toPublicParams(xHex, yHex, domainParameters);
    }

    /**
     * 转换为SM2的ECPublicKeyParameters
     *
     * @param xBytes 公钥X
     * @param yBytes 公钥Y
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toSm2Params(byte[] xBytes, byte[] yBytes) {
        return ECKeyUtil.toSm2PublicParams(xBytes, yBytes);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param xBytes           公钥X
     * @param yBytes           公钥Y
     * @param domainParameters ECDomainParameters
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toParams(byte[] xBytes, byte[] yBytes, ECDomainParameters domainParameters) {
        return ECKeyUtil.toPublicParams(xBytes, yBytes, domainParameters);
    }

    /**
     * 公钥转换为 {@link ECPublicKeyParameters}
     *
     * @param publicKey 公钥，传入null返回null
     * @return {@link ECPublicKeyParameters}或null
     */
    public static ECPublicKeyParameters toParams(PublicKey publicKey) {
        return ECKeyUtil.toPublicParams(publicKey);
    }

    /**
     * 私钥转换为 {@link ECPrivateKeyParameters}
     *
     * @param privateKey 私钥，传入null返回null
     * @return {@link ECPrivateKeyParameters}或null
     */
    public static ECPrivateKeyParameters toParams(PrivateKey privateKey) {
        return ECKeyUtil.toPrivateParams(privateKey);
    }

    /**
     * 读取PEM格式的私钥
     *
     * @param pemStream pem流
     * @return {@link PrivateKey}
     * @since 5.2.5
     * @see PemUtil#readPemPrivateKey(InputStream)
     */
    public static PrivateKey readPemPrivateKey(InputStream pemStream) {
        return PemUtil.readPemPrivateKey(pemStream);
    }

    /**
     * 读取PEM格式的公钥
     *
     * @param pemStream pem流
     * @return {@link PublicKey}
     * @since 5.2.5
     * @see PemUtil#readPemPublicKey(InputStream)
     */
    public static PublicKey readPemPublicKey(InputStream pemStream) {
        return PemUtil.readPemPublicKey(pemStream);
    }

    /**
     * Java中的PKCS#8格式私钥转换为OpenSSL支持的PKCS#1格式
     *
     * @param privateKey PKCS#8格式私钥
     * @return PKCS#1格式私钥
     * @since 5.5.9
     */
    public static byte[] toPkcs1(PrivateKey privateKey){
        final PrivateKeyInfo pkInfo = PrivateKeyInfo.getInstance(privateKey.getEncoded());
        try {
            return pkInfo.parsePrivateKey().toASN1Primitive().getEncoded();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * Java中的X.509格式公钥转换为OpenSSL支持的PKCS#1格式
     *
     * @param publicKey X.509格式公钥
     * @return PKCS#1格式公钥
     * @since 5.5.9
     */
    public static byte[] toPkcs1(PublicKey publicKey){
        final SubjectPublicKeyInfo spkInfo = SubjectPublicKeyInfo
                .getInstance(publicKey.getEncoded());
        try {
            return spkInfo.parsePublicKey().getEncoded();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }
}
