package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.constants.KeyStatus;
import org.dms.utils.ModelUtil;

public class Key {
    @AutoIncrement
    private Integer id;
    private Boolean isPrimary;
    private KeyStatus keyStatus;

    public Key(Boolean isPrimary) {
        ModelUtil.handleAutoIncrement(this);
        this.isPrimary = isPrimary;
        this.keyStatus = KeyStatus.AVAILABLE;
    }

    public KeyStatus getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(KeyStatus keyStatus) {
        this.keyStatus = keyStatus;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
