package kingja.activitybus.compiler;

/**
 * Description:TODO
 * Create Time:2017/7/19 13:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PassenageBean {
    private String fieldType;
    private String fieldName;

    public PassenageBean(String fieldType, String fieldName) {
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
