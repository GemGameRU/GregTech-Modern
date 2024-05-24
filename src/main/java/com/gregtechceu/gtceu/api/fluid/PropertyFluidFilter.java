package com.gregtechceu.gtceu.api.fluid;

import com.gregtechceu.gtceu.api.capability.IPropertyFluidFilter;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;

public class PropertyFluidFilter implements IPropertyFluidFilter {

    private final Object2BooleanMap<GTFluidAttribute> containmentPredicate = new Object2BooleanOpenHashMap<>();

    @Getter
    private final int maxFluidTemperature;
    @Getter
    private final boolean gasProof;
    @Getter
    private final boolean cryoProof;
    @Getter
    private final boolean plasmaProof;

    public PropertyFluidFilter(int maxFluidTemperature,
                               boolean gasProof,
                               boolean acidProof,
                               boolean cryoProof,
                               boolean plasmaProof) {
        this.maxFluidTemperature = maxFluidTemperature;
        this.gasProof = gasProof;
        if (acidProof) setCanContain(GTFluidAttributes.ACID, true);
        this.cryoProof = cryoProof;
        this.plasmaProof = plasmaProof;
    }

    @Override
    public boolean canContain(@NotNull FluidState state) {
        return switch (state) {
            case LIQUID -> true;
            case GAS -> gasProof;
            case PLASMA -> plasmaProof;
        };
    }

    @Override
    public boolean canContain(@NotNull GTFluidAttribute attribute) {
        return containmentPredicate.getBoolean(attribute);
    }

    @Override
    public void setCanContain(@NotNull GTFluidAttribute attribute, boolean canContain) {
        containmentPredicate.put(attribute, canContain);
    }

    @Override
    public @NotNull @UnmodifiableView Collection<@NotNull GTFluidAttribute> getContainedAttributes() {
        return containmentPredicate.keySet();
    }

    @Override
    public String toString() {
        return "SimplePropertyFluidFilter{" +
                "maxFluidTemperature=" + maxFluidTemperature +
                ", gasProof=" + gasProof +
                ", cryoProof=" + cryoProof +
                ", plasmaProof=" + plasmaProof +
                ", containmentPredicate=" + containmentPredicate +
                '}';
    }
}
