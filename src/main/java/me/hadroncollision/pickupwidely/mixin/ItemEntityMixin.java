package me.hadroncollision.pickupwidely.mixin;

import me.hadroncollision.pickupwidely.config.ModConfigs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity
{
    @Shadow public abstract void onPlayerCollision(PlayerEntity player);

    public ItemEntityMixin(EntityType<?> type, World world)
    {
        super(type, world);
    }


    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo ci)
    {
        byte radius;
        if (ModConfigs.PICKUP_RADIUS < 1)
        {
            radius = 3;
        } else
        {
            radius = ModConfigs.PICKUP_RADIUS;
        }
        Vec3d itemPos = this.getPos();
        BlockPos blockPos = new BlockPos((int) itemPos.getX(), (int) itemPos.getY(), (int) itemPos.getZ());
        Box box = new Box(blockPos).expand(radius);
        List<PlayerEntity> players = world.getNonSpectatingEntities(PlayerEntity.class, box);

        if (!players.isEmpty())
        {
            this.onPlayerCollision(players.get(0));
        }
    }
}
