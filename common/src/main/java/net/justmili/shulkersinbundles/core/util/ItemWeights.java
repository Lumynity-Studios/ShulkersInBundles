package net.justmili.shulkersinbundles.core.util;

import org.apache.commons.lang3.math.Fraction;

public class ItemWeights {
    private static Fraction SHULKER = Fraction.getFraction(1, 16);

    public static Fraction getShulker() {
        return SHULKER;
    }

    /**
     * Sets the weight a Shulker Box takes up inside a Bundle.
     * <p>
     * Intended for addons and other mods to change the Shulker Box item weight value.
     * Call this once during mod initialization to override the default weight.
     * Bundles have a total capacity of {@code 1}, so the value is
     * the fraction of that capacity used by a single Shulker Box.
     * <p>
     * The default is {@code 1/16}.
     *
     * @param fraction the new weight of a Shulker Box, as a fraction of a Bundle's capacity
     */
    public static void setShulker(Fraction fraction) {
        SHULKER = fraction;
    }

    /**
     * Sets the weight a Shulker Box takes up inside a Bundle to {@code 1/denominator}.
     * <p>
     * Convenience overload of {@link #setShulker(Fraction)} for unit fractions.
     * For example, {@code setShulker(8)} makes a Shulker Box take up {@code 1/8} of a Bundle's capacity.
     * <p>
     * If the denominator is {@code 0} or lower, it falls back to {@code 1}.
     *
     * @param denominator the denominator of the new weight, values of {@code 0} or lower become {@code 1}
     */
    public static void setShulker(int denominator) {
        if (denominator <= 0) denominator = 1;
        SHULKER = Fraction.getFraction(1, denominator);
    }
}
