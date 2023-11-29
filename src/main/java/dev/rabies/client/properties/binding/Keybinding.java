package dev.rabies.client.properties.binding;

import lombok.Data;

@Data
public class Keybinding {
    private int keyCode;

    protected Keybinding(int keyCode) {
        this.keyCode = keyCode;
    }

    public static Keybinding fromKey(int keyCode) {
        return new Keybinding(keyCode);
    }

    public static Keybinding none() {
        return fromKey(-1);
    }
}
