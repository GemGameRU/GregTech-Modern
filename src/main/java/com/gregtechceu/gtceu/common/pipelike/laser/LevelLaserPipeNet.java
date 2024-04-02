package com.gregtechceu.gtceu.common.pipelike.laser;

import com.gregtechceu.gtceu.api.pipenet.LevelPipeNet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class LevelLaserPipeNet extends LevelPipeNet<LaserPipeProperties, LaserPipeNet> {
    private static final String DATA_ID = "gtceu_laser_pipe_net";

    public static LevelLaserPipeNet getOrCreate(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(new SavedData.Factory<>(() -> new LevelLaserPipeNet(serverLevel), tag -> new LevelLaserPipeNet(serverLevel, tag)), DATA_ID);
    }

    public LevelLaserPipeNet(ServerLevel serverLevel) {
        super(serverLevel);
    }

    public LevelLaserPipeNet(ServerLevel serverLevel, CompoundTag tag) {
        super(serverLevel, tag);
    }

    @Override
    protected LaserPipeNet createNetInstance() {
        return new LaserPipeNet(this);
    }
}
