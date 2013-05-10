package com.yogapppackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.caucho.hessian.server.HessianServlet;

public class BasicService extends HessianServlet implements BasicAPI {

    @Override
    public String TestInfoShow() {
        // TODO Auto-generated method stub
        return "hello";
    }

    @Override
    public JSONObject SelectAllCepActive(String userid) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cepid", "某某活动1");
            map.put("ceptitle", "活动标题1");
            map.put("cepcontent", "活动标题1");
            map.put("ceptime", "活动标题1");
            map.put("cepplace", "活动标题1");
            map.put("joinnum", "活动标题1");
            map.put("signupnum", "活动标题1");
            map.put("commentnum", "活动标题1");
            map.put("ceppicture", "http://guoxiaomei.b0.upaiyun.com/p/" + (2 + i) + ".jpg");
            map.put("flag", "1");
            Map<String, Object> commentdetail = new HashMap<String, Object>();
            List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < 8; j++) {
                commentdetail.put("cepid", "某某活动1");
                commentdetail.put("commentid", "某评论者ID1");
                commentdetail.put("picturehttp", "http://tp2.sinaimg.cn/1813080181/180/5641600999/1");
                commentdetail.put("commentname", "JOHN");
                commentdetail.put("commenttime", "20130818090000");
                commentdetail.put("commentconent", "评论内容1");
                commentList.add(commentdetail);
            }
            map.put("commentdetail", commentList);
            list.add(map);
        }
        JSONArray array = JSONArray.fromObject(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("AllCepInfo", array);
        return jsonObject;
    }

    /**
     * http://guoxiaomei.b0.upaiyun.com/p/2.jpg http://guoxiaomei.b0.upaiyun.com/p/3.jpg
     * http://guoxiaomei.b0.upaiyun.com/p/4.jpg
     */
    @Override
    public JSONObject SelectTheOneCepActive(String cepid, String userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cepid", "某某活动1");
        map.put("ceptitle", "活动标题1");
        map.put("cepcontent", "活动标题1");
        map.put("ceptime", "活动标题1");
        map.put("cepplace", "活动标题1");
        map.put("joinnum", "活动标题1");
        map.put("signupnum", "活动标题1");
        map.put("commentnum", "活动标题1");
        map.put("flag", "1");
        map.put("ceppicture", "http://guoxiaomei.b0.upaiyun.com/p/2.jpg");
        Map<String, Object> commentdetail = new HashMap<String, Object>();
        List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < 8; j++) {
            commentdetail.put("cepid", "某某活动1");
            commentdetail.put("commentid", "某评论者ID1");
            commentdetail.put("picturehttp", "http://tp2.sinaimg.cn/1813080181/180/5641600999/1");
            commentdetail.put("commentname", "JOHN");
            commentdetail.put("commenttime", "20130818090000");
            commentdetail.put("commentconent", "评论内容1");
            commentList.add(commentdetail);
        }
        map.put("commentdetail", commentList);
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONObject json = new JSONObject();
        json.put("TheOneCepInfo", jsonObject);
        return json;
    }

    @Override
    public JSONObject PrecontractSignUpCepActive(String cepid, String userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sucessmark", "P1");
        map.put("sucesstext", "您已预约成功，请到XXX服务台确认，服务台地址：XXX，联系电话：XXX");
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONObject json = new JSONObject();
        json.put("PrecontractSignUp", jsonObject);
        return json;
    }

    @Override
    public JSONObject PrecontractCancelCepActive(String cepid, String userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sucessmark", "C1");
        map.put("sucesstext", "该活动取消成功");
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONObject json = new JSONObject();
        json.put("PrecontractCancel", jsonObject);
        return json;
    }

    @Override
    public JSONObject ConfirmCepActive(String cepid, String userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("confirmmark", "Q1");
        map.put("confirmtext", "您预约的CEP活动获得批准");
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONObject json = new JSONObject();
        json.put("ConfirmCepActive", jsonObject);
        return json;
    }

    @Override
    public JSONObject SelectConfirmCepActive(String userid) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cepid", "某某活动1");
            map.put("cepstate", "P1");
            map.put("ceptitle", "活动标题1");
            map.put("cepcontent", "活动内容的具体说明1");
            map.put("ceptime", "20130818090000");
            map.put("ceptime", "20130818090000");
            map.put("cepplace", "某地点1");
            map.put("signuptime", "20130818090000");
            list.add(map);
        }
        JSONArray array = JSONArray.fromObject(list);
        JSONObject json = new JSONObject();
        json.put("SelectConfirmCepActive", array);
        return json;
    }

    @Override
    public JSONObject CepActiveComment(String cepid, String userid, String commentcontent) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("confirmmark", "1");
        map.put("confirmtext", "评论成功");
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONObject json = new JSONObject();
        json.put("CepActiveComment", jsonObject);
        return json;
    }

    @Override
    public JSONObject SignInCepActive(String twobarcode, String userid, String lng, String lat) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("signinmark", "S1");
        map.put("signintext", "活动签到成功");
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONObject json = new JSONObject();
        json.put("SignInCepActive", jsonObject);
        return json;
    }

}
