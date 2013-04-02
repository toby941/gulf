package com.gulf.service;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRcodeService {

    /**
     * 获取2维码 http://mp.weixin.qq.com/wiki/index.php?title=%E6%B6%88%E6%81%AF%E6%
     * 8E%A5%E5%8F%A3%E6%8C%87%E5%8D%97#.E5.9B.BE.E7.89.87.E6.B6.88.E6.81.AF<br/>
     * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
     * 
     * @param contents
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getQrCodeImage(String contents, int width, int height) {
        Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            // MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
