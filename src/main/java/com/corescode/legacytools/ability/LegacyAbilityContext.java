package com.corescode.legacytools.ability;

public final class LegacyAbilityContext {

    private static final ThreadLocal<Boolean> BREAKING =
            ThreadLocal.withInitial(() -> false);

    private LegacyAbilityContext() {
    }

    public static boolean isBreaking() {
        return BREAKING.get();
    }

    public static void beginBreaking() {
        BREAKING.set(true);
    }

    public static void endBreaking() {
        BREAKING.set(false);
    }
}