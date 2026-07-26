package com.corescode.legacytools.mixin;

import com.corescode.legacytools.world.LegacyMiningContext;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {

    @Shadow
    @Final
    public ServerPlayer player;

    @Inject(
            method = "handlePlayerAction",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayerGameMode;handleBlockBreakAction(Lnet/minecraft/core/BlockPos;Lnet/minecraft/network/protocol/game/ServerboundPlayerActionPacket$Action;Lnet/minecraft/core/Direction;II)V"
            )
    )
    private void legacytools$captureDirection(
            ServerboundPlayerActionPacket packet,
            CallbackInfo ci
    ) {

        if (packet.getAction() == ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK) {

            LegacyMiningContext.set(
                    player.getUUID(),
                    packet.getDirection()
            );

        }
    }
}