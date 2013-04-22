package com.gulf.service.image;

import java.util.Calendar;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.gulf.domain.Image;
import com.gulf.service.BaseService;

@IocBean
public class ImageService extends BaseService {
    @Inject
    private UpYun upYun;

    protected String api_domain;

    public UpYun getUpYun() {
        return upYun;
    }

    public Integer getMaxId() {
        return dao.getMaxId(Image.class);
    }

    /**
     * 根据image id直接构造upyun url图片链接
     * 
     * @param id
     * @return
     */
    public String getImageUrlByTypeAndId(Integer id) {
        String url = getUpYun().getAccessUrl(id, "jpg", isDev());
        return UpYunUtils.buildFullPicPath(url);
    }

    public String saveImage(byte[] content) throws Exception {
        Integer id = getMaxId();
        String accessUrl = getUpYun().getAccessUrl(id, "jpg", isDev());
        getUpYun().writeFile(accessUrl, content);
        addImage(id, accessUrl);
        return getImageUrlByTypeAndId(id);
    }

    public void addImage(Integer id, String url) {
        Image image = new Image();
        image.setUrl(url);
        image.setAddTime(Calendar.getInstance().getTime());
        dao.insert(image);

    }
}
