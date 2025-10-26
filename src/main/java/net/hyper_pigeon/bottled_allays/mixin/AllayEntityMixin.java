package net.hyper_pigeon.bottled_allays.mixin;

import net.hyper_pigeon.bottled_allays.BottledAllays;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AllayEntity.class)
public abstract class AllayEntityMixin extends PathAwareEntity {

    @Shadow public abstract void writeCustomDataToNbt(NbtCompound nbt);

    protected AllayEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob",at = @At("HEAD"), cancellable = true)
    private void bottleAllay(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        if(!this.world.isClient) {
            ItemStack itemStack = player.getStackInHand(hand);
            if(itemStack.getItem() == Items.GLASS_BOTTLE && player.isSneaking()){
                ItemStack newItemStack = new ItemStack(BottledAllays.BOTTLE_OF_ALLAY);
                writeCustomDataToNbt(newItemStack.getOrCreateNbt());
                if (this.getName() != null)
                    newItemStack.getOrCreateNbt().putString("allay_name", Text.Serializer.toJson(this.getName()));
                player.giveItemStack(newItemStack);
                itemStack.decrement(1);
                this.discard();
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }

    }


}
