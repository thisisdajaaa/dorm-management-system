package org.dms.models.key;

public class Key {
    private Integer id;
    private Boolean isPrimary;

    public Key(Integer id, Boolean isPrimary) {
        this.id = id;
        this.isPrimary = isPrimary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
