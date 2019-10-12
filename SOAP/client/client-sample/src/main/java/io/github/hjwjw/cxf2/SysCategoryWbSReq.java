
package io.github.hjwjw.cxf2;

public class SysCategoryWbSReq {

    protected String categoryCode;
    protected String categoryName;
    protected int enabledFlag;

    /**
     * 获取categoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * 设置categoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * 获取categoryName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置categoryName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * 获取enabledFlag属性的值。
     * 
     */
    public int getEnabledFlag() {
        return enabledFlag;
    }

    /**
     * 设置enabledFlag属性的值。
     * 
     */
    public void setEnabledFlag(int value) {
        this.enabledFlag = value;
    }

    @Override
    public String toString() {
        return "SysCategoryWbSReq{" +
                "categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", enabledFlag=" + enabledFlag +
                '}';
    }
}
