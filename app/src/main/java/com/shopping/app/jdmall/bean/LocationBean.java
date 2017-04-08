package com.shopping.app.jdmall.bean;

import java.util.List;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class LocationBean {


    /**
     * addressList : [{"addressArea":"abc","addressDetail":"abc","city":"abc","id":133,"isDefault":0,"name":"abc","phoneNumber":"123","province":"abc","zipCode":"12345"},{"addressArea":"洪山区","addressDetail":"文华路文华学院","city":"武汉市","id":134,"isDefault":0,"name":"张瑞丽","phoneNumber":"18986104910","province":"湖北","zipCode":"1008611"},{"addressArea":"洪山区","addressDetail":"街道口地铁c口","city":"武汉市","id":139,"isDefault":0,"name":"itcast","phoneNumber":"027-81970008","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":146,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"}]
     * response : addressList
     */

    private String response;
    /**
     * addressArea : abc
     * addressDetail : abc
     * city : abc
     * id : 133
     * isDefault : 0
     * name : abc
     * phoneNumber : 123
     * province : abc
     * zipCode : 12345
     */

    private List<AddressListBean> addressList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean {
        private String addressArea;
        private String addressDetail;
        private String city;
        private int id;
        private int isDefault;
        private String name;
        private String phoneNumber;
        private String province;
        private String zipCode;

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
