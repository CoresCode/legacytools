package com.corescode.legacytools.util;

public final class ProgressBarUtils {

    private static final int BAR_LENGTH = 10;

    private ProgressBarUtils() {
    }

    public static String createProgressBar(int progress, int requiredProgress) {

        if (requiredProgress <= 0) {
            return "□□□□□□□□□□";
        }

        int filled = (int) Math.floor(
                (double) progress / requiredProgress * BAR_LENGTH
        );

        if (filled > BAR_LENGTH) {
            filled = BAR_LENGTH;
        }

        StringBuilder builder = new StringBuilder(BAR_LENGTH);

        for (int i = 0; i < BAR_LENGTH; i++) {

            if (i < filled) {
                builder.append('■');
            } else {
                builder.append('□');
            }

        }

        return builder.toString();
    }

}