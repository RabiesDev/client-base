package dev.rabies.client.properties.binding;

import lombok.Data;

@Data
public class KeyBinding {
    private BindMode bindMode;
    private int keyCode;

    protected KeyBinding(BindMode bindMode, int keyCode) {
        this.bindMode = bindMode;
        this.keyCode = keyCode;
    }

    public static KeyBinding create(BindMode bindMode, int keyCode) {
        return new KeyBinding(bindMode, keyCode);
    }

    public static KeyBinding fromKey(int keyCode) {
        return new KeyBinding(BindMode.TOGGLE, keyCode);
    }

    public static KeyBinding none() {
        return fromKey(-1);
    }
}
