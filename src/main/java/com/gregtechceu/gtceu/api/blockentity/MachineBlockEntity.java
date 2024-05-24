package com.gregtechceu.gtceu.api.blockentity;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;

import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import com.lowdragmc.lowdraglib.syncdata.managed.MultiManagedStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import lombok.Getter;

import java.util.Set;

/**
 * @author KilaBash
 * @date 2023/2/17
 * @implNote MetaMachineBlockEntity
 */
public class MachineBlockEntity extends BlockEntity implements IMachineBlockEntity {

    public final MultiManagedStorage managedStorage = new MultiManagedStorage();
    @Getter
    public final MetaMachine metaMachine;
    private final long offset = GTValues.RNG.nextInt(20);

    protected MachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.metaMachine = getDefinition().createMetaMachine(this);
    }

    public static MachineBlockEntity createBlockEntity(BlockEntityType<?> type, BlockPos pos,
                                                       BlockState blockState) {
        return new MachineBlockEntity(type, pos, blockState);
    }

    public static void onBlockEntityRegister(BlockEntityType<BlockEntity> type) {}

    @Override
    public MultiManagedStorage getRootStorage() {
        return managedStorage;
    }

    @Override
    public boolean triggerEvent(int id, int para) {
        if (id == 1) { // chunk re render
            if (level != null && level.isClientSide) {
                scheduleRenderUpdate();
            }
            return true;
        }
        return false;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        metaMachine.onUnload();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        metaMachine.onLoad();
    }

    @Override
    public boolean shouldRenderGrid(Player player, ItemStack held, Set<GTToolType> toolTypes) {
        return metaMachine.shouldRenderGrid(player, held, toolTypes);
    }

    @Override
    public ResourceTexture sideTips(Player player, Set<GTToolType> toolTypes, Direction side) {
        return metaMachine.sideTips(player, toolTypes, side);
    }

    @Override
    public void setChanged() {
        if (getLevel() != null) {
            getLevel().blockEntityChanged(getBlockPos());
        }
    }
}