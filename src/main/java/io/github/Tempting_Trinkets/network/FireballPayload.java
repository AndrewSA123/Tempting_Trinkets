package io.github.Tempting_Trinkets.network;

import io.github.Tempting_Trinkets.TemptingTrinkets;
import io.github.Tempting_Trinkets.item.custom.RingOfFire;
import io.github.Tempting_Trinkets.trinket.Trinkets;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record FireballPayload() implements CustomPacketPayload {
    public static final FireballPayload INSTANCE = new FireballPayload();

    public static final CustomPacketPayload.Type<FireballPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(TemptingTrinkets.MODID, "fireball"));

    public static final StreamCodec<ByteBuf, FireballPayload> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(FireballPayload payload, IPayloadContext context) {
        Player player = context.player();
        ItemStack ring = Trinkets.find(player, RingOfFire.class);
        if (!ring.isEmpty()) {
            ((RingOfFire) ring.getItem()).createFireBall(player, ring);
        }
    }
}