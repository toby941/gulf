package com.gulf.service.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 上传图片处理
 * 
 * @author toby
 */
public class ImageDomain {

    private static final Logger log = Logger.getLogger(ImageDomain.class);

    public static final String IMAGE_FILE_TYPE_JPG = "jpg";
    public static final String IMAGE_FILE_TYPE_PNG = "png";

    public static String[] image_type_table = {"EMMS_COMPANY", // 0-公司表
            "EMMS_ACTIVITY", // 1-活动签到表
            "EMMS_COMPANY_ATTACHED",// 2-公司附属表
            "EMMS_MEMBER_CHECKIN", // 3-签到记录
            "EMMS_BOOKING", // 4-预订表
            "EMMS_CLASS", // 5-栏目总表
            "EMMS_CLASS_RELATION", // 6-栏目内容关联表
            "EMMS_NEWS", // 7-新闻表
            "EMMS_EXHIBITION", // 8-展示表- 跟新闻结构一样
            "EMMS_GALLERY", // 9-图片栏目
            "EMMS_CLOTH_EXHIBITION", // 10-高级定制服栏目
            "EMMS_VIDEO", // 11-视频表
            "EMMS_NEWS_THUMBNAILS", // 12-新闻条目缩略图表
            "EMMS_PAGE_ABOUT_US", // 13-“关于我们”页面图片
            "EMMS_GALLERY_THUMBNAILS", // 14 图册小图
            "EMMS_CLOTH_EXHIBITION_THUMBNAILS", // 15 高级定制服展示小图
            "EMMS_PRODUCTS", // 16
            "PASSBOOK_ICON", // 17 passbook icon image (all) 58x58
            "PASSBOOK_LOGO", // 18 passbook logo image (all) 160x50
            "PASSBOOK_FOOTER", // 19 passbook footer image (boarding pass) 286x15
            "PASSBOOK_STRIP", // 20 passbook strp image (coupon,event ticket 312x84,store card) (312x110 with a square
                              // barcode) 312x123
            "PASSBOOK_BACKGROUND", // 21 passbook background image (event ticket) 180x220
            "PASSBOOK_THUMBNAIL", // 22 passbook thumbnail image (event ticket,generic) 90x90
            //
            "LOOP_ICON", // 23 passbook icon image (all) 58x58
            "LOOP_LOGO", // 24 passbook logo image (all) 160x50
            "LOOP_FOOTER", // 25 passbook footer image (boarding pass) 286x15
            "LOOP_STRIP", // 26 passbook strp image (coupon,event ticket 312x84,store card) (312x110 with a square
                          // barcode) 312x123
            "LOOP_BACKGROUND", // 27 passbook background image (event ticket) 180x220
            "LOOP_THUMBNAIL" // 28 passbook thumbnail image (event ticket,generic) 90x90
    };
    public final static Integer TABLE_EMMS_COMPANY = 0;
    public final static Integer TABLE_EMMS_ACTIVITY = 1;
    public final static Integer TABLE_EMMS_COMPANY_ATTACHED = 2;
    public final static Integer TABLE_EMMS_MEMBER_CHECKIN = 3;
    public final static Integer TABLE_EMMS_BOOKING = 4;
    public final static Integer TABLE_EMMS_CLASS = 5;
    public final static Integer TABLE_EMMS_CLASS_RELATION = 6;
    public final static Integer TABLE_EMMS_NEWS = 7;
    public final static Integer TABLE_EMMS_EXHIBITION = 8;
    public final static Integer TABLE_EMMS_GALLERY = 9;
    public final static Integer TABLE_EMMS_CLOTH_EXHIBITION = 10;
    public final static Integer TABLE_EMMS_VIDEO = 11;
    public final static Integer TABLE_EMMS_NEWS_THUMBNAILS = 12;
    public final static Integer TABLE_EMMS_PAGE_ABOUT_US = 13;
    public final static Integer TABLE_EMMS_GALLERY_THUMBNAILS = 14;
    public final static Integer TABLE_EMMS_CLOTH_EXHIBITION_THUMBNAILS = 15;
    public final static Integer TABLE_EMMS_PRODUCTS = 16;
    public final static Integer PASSBOOK_ICON = 17;
    public final static Integer PASSBOOK_LOGO = 18;
    public final static Integer PASSBOOK_FOOTER = 19;
    public final static Integer PASSBOOK_STRIP = 20;
    public final static Integer PASSBOOK_BACKGROUND = 21;
    public final static Integer PASSBOOK_THUMBNAIL = 22;
    //
    public final static Integer LOOP_ICON = 23;
    public final static Integer LOOP_LOGO = 24;
    public final static Integer LOOP_FOOTER = 25;
    public final static Integer LOOP_STRIP = 26;
    public final static Integer LOOP_BACKGROUND = 27;
    public final static Integer LOOP_THUMBNAIL = 28;

    private double imgWidth;// 图片宽

    private double imgHeight;// 图片高

    private double imgDemoWidth;// 图片demo宽

    private double imgDemoHeight;// 图片demo高

    private String imgLocalPatch;// 图片本地路径

    private int imgType;// 图片类型

    private int method;// 压缩方式

    private BufferedImage imgBuff;// 原图

    /**
     * 通过本地路径构造
     * 
     * @param imgLocalPatch
     */
    public ImageDomain(String imgLocalPatch) {
        this.imgLocalPatch = imgLocalPatch;
    }

    /**
     * 通过输入流构建
     * 
     * @param imgLocalPatch
     * @throws IOException
     */
    public ImageDomain(InputStream inputStream) throws IOException {
        imgBuff = javax.imageio.ImageIO.read(inputStream);
        imgWidth = imgBuff.getWidth(null);
        imgHeight = imgBuff.getHeight(null);
    }

    public static EmmsImage getEmmsImage(byte[] content, String emmsCompanyId, String userId, Integer imageType,
            Integer belongToId, String imageFormat) throws IOException {
        EmmsImage image = new EmmsImage();
        InputStream inputStream = new ByteArrayInputStream(content);
        BufferedImage img = javax.imageio.ImageIO.read(inputStream);
        image.setWidth(img.getWidth());
        image.setHigh(img.getHeight());
        image.setImageType(imageType);
        byte[] imgByte = null;
        if (StringUtils.isBlank(imageFormat)) {
            imgByte = transformeFileTypeToJPG(content);
        }
        else {
            imgByte = transformFileTypeToCustom(content, imageFormat);
        }
        image.setContent(imgByte);
        image.setAddTime(Calendar.getInstance().getTime());
        image.setSize(imgByte.length / 1024);
        image.setBelongToId(belongToId);
        image.setUserId(userId);
        image.setImageFormat(StringUtils.isBlank(imageFormat) ? ImageDomain.IMAGE_FILE_TYPE_JPG : imageFormat);
        image.setEmmsCompanyId(Integer.parseInt(emmsCompanyId));
        image.setAddOper(Integer.parseInt(userId));
        return image;
    }
    
    public static EmmsImage getEmmsImage(BufferedImage img, String emmsCompanyId, String userId, Integer imageType,
            Integer belongToId, String imageFormat) throws IOException {
        EmmsImage image = new EmmsImage();
        image.setWidth(img.getWidth());
        image.setHigh(img.getHeight());
        image.setImageType(imageType);
        image.setAddTime(Calendar.getInstance().getTime());
        image.setBelongToId(belongToId);
        image.setUserId(userId);
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        boolean flag = ImageIO.write(img, "png", out);
        image.setContent(out.toByteArray());
        image.setImageFormat(StringUtils.isBlank(imageFormat) ? ImageDomain.IMAGE_FILE_TYPE_JPG : imageFormat);
        image.setEmmsCompanyId(Integer.parseInt(emmsCompanyId));
        image.setAddOper(Integer.parseInt(userId));
        return image;
    }

    /**
     * 图片格式转换
     * 
     * @param source
     * @return
     * @throws IOException
     */
    public static byte[] transformeFileTypeToJPG(byte[] source) throws IOException {
        byte[] result = source;
        ByteArrayOutputStream baos = null;
        if (source[0] != 0xFF || source[1] != 0xD8) {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(source));
            baos = new ByteArrayOutputStream();
            ImageIO.write(image, IMAGE_FILE_TYPE_JPG, baos);
            result = baos.toByteArray();
            IOUtils.closeQuietly(baos);
            return result;
        }
        else {
            return result;
        }
    }

    public static byte[] transformeFileTypeToPNG(byte[] source) throws IOException {
        byte[] result = source;
        ByteArrayOutputStream baos = null;
        if (source[0] != 0xFF || source[1] != 0xD8) {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(source));
            baos = new ByteArrayOutputStream();
            ImageIO.write(image, IMAGE_FILE_TYPE_PNG, baos);
            result = baos.toByteArray();
            IOUtils.closeQuietly(baos);
            return result;
        }
        else {
            return result;
        }
    }

    public static byte[] transformFileTypeToCustom(byte[] source, String imageFormat) throws IOException {
        if (StringUtils.isBlank(imageFormat) || StringUtils.containsIgnoreCase(imageFormat, IMAGE_FILE_TYPE_JPG)) {
            return transformeFileTypeToJPG(source);
        }
        else if (StringUtils.containsIgnoreCase(imageFormat, IMAGE_FILE_TYPE_PNG)) {
            return transformeFileTypeToPNG(source);
        }
        return null;
    }

    /**
     * 压缩图片
     * 
     * @param w
     * @param h
     * @param modality 0 等比最小 1 按宽 2按高 3定值
     * @throws IOException
     */
    public byte[] resize(int w, int h, int modality) throws IOException {
        imgDemoWidth = w;
        imgDemoHeight = h;
        // 得到合适的压缩大小，按比例。
        if (modality == 0) {
            if (imgWidth >= imgHeight) {
                h = (int) Math.round((imgHeight * w * 1.0 / imgWidth));
            }
            else {
                w = (int) Math.round((imgWidth * h * 1.0 / imgHeight));
            }
        }
        else if (modality == 1) {
            h = (int) Math.round((imgHeight * w * 1.0 / imgWidth));
        }

        // 构建图片对象
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // 绘制缩小后的图
        image.getGraphics().drawImage(imgBuff, 0, 0, w, h, null);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        ImageIO.write(image, IMAGE_FILE_TYPE_JPG, bao);
        byte[] data = bao.toByteArray();
        // byte[] data = ((java.awt.image.DataBufferByte)
        // _image.getData().getDataBuffer()).getData();

        return data;
        // 输出到文件流
        // FileOutputStream out = new FileOutputStream(destFile);
        // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // encoder.encode(_image);
        // out.flush();
        // out.close();
    }

    /**
     * 保存图片
     * 
     * @param img 原图路径
     * @param dest 目标图路径
     * @param top 选择框的左边y坐标
     * @param left 选择框的左边x坐标
     * @param width 选择框宽度
     * @param height 选择框高度
     * @return
     * @throws IOException
     */
    public static boolean saveImage(File img, String dest, int top, int left, int width, int height) throws IOException {
        File fileDest = new File(dest);
        if (!fileDest.getParentFile().exists()) {
            fileDest.getParentFile().mkdirs();
        }
        String ext = getExtension(dest).toLowerCase();
        BufferedImage bi = ImageIO.read(img);
        height = Math.min(height, bi.getHeight());
        width = Math.min(width, bi.getWidth());
        if (height <= 0) {
            height = bi.getHeight();
        }
        if (width <= 0) {
            width = bi.getWidth();
        }
        top = Math.min(Math.max(0, top), bi.getHeight() - height);
        left = Math.min(Math.max(0, left), bi.getWidth() - width);

        BufferedImage bi_cropper = bi.getSubimage(left, top, width, height);
        return ImageIO.write(bi_cropper, ext.equals("png") ? "png" : "jpeg", fileDest);
    }

    public static String getExtension(File f) {
        return (f != null) ? getExtension(f.getName()) : "";
    }

    public static String getExtension(String filename) {
        return getExtension(filename, "");
    }

    public static String getExtension(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > -1) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return defExt;
    }

    public static String trimExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > -1) && (i < (filename.length()))) {
                return filename.substring(0, i);
            }
        }
        return filename;
    }

    /**
     * @return the imgWidth
     */
    public double getImgWidth() {
        return imgWidth;
    }

    /**
     * @param imgWidth the imgWidth to set
     */
    public void setImgWidth(double imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * @return the imgHeight
     */
    public double getImgHeight() {
        return imgHeight;
    }

    /**
     * @param imgHeight the imgHeight to set
     */
    public void setImgHeight(double imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * @return the imgDemoWidth
     */
    public double getImgDemoWidth() {
        return imgDemoWidth;
    }

    /**
     * @param imgDemoWidth the imgDemoWidth to set
     */
    public void setImgDemoWidth(double imgDemoWidth) {
        this.imgDemoWidth = imgDemoWidth;
    }

    /**
     * @return the imgDemoHeight
     */
    public double getImgDemoHeight() {
        return imgDemoHeight;
    }

    /**
     * @param imgDemoHeight the imgDemoHeight to set
     */
    public void setImgDemoHeight(double imgDemoHeight) {
        this.imgDemoHeight = imgDemoHeight;
    }

    /**
     * @return the imgLocalPatch
     */
    public String getImgLocalPatch() {
        return imgLocalPatch;
    }

    /**
     * @param imgLocalPatch the imgLocalPatch to set
     */
    public void setImgLocalPatch(String imgLocalPatch) {
        this.imgLocalPatch = imgLocalPatch;
    }

    /**
     * @return the imgType
     */
    public int getImgType() {
        return imgType;
    }

    /**
     * @param imgType the imgType to set
     */
    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    /**
     * @return the method
     */
    public int getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * @return the imgBuff
     */
    public BufferedImage getImgBuff() {
        return imgBuff;
    }

    /**
     * @param imgBuff the imgBuff to set
     */
    public void setImgBuff(BufferedImage imgBuff) {
        this.imgBuff = imgBuff;
    }
}
