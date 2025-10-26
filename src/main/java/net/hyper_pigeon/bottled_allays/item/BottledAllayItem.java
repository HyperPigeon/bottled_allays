package net.hyper_pigeon.bottled_allays.item;

import eu.pb4.polymer.api.item.PolymerItem;
import eu.pb4.polymer.api.item.PolymerItemUtils;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BottledAllayItem extends Item implements PolymerItem {
    public BottledAllayItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        AllayEntity allayEntity = EntityType.ALLAY.create(world);
        ItemStack allayBottle = user.getStackInHand(hand);

        assert allayEntity != null;
        allayEntity.readCustomDataFromNbt(user.getStackInHand(hand).getOrCreateNbt());
        allayEntity.refreshPositionAndAngles(user.getBlockPos(),0,0);
        world.spawnEntity(allayEntity);

        if (allayBottle.getOrCreateNbt().contains("allay_name")) {
            NbtCompound allayData = new NbtCompound();
            allayEntity.writeNbt(allayData);
            allayData.putString("CustomName", allayBottle.getNbt().getString("allay_name"));
            allayEntity.readNbt(allayData);
        }

        allayBottle.decrement(1);
        user.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
        return TypedActionResult.success(user.getStackInHand(hand));
    }



    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.DRAGON_BREATH;
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, ServerPlayerEntity player) {
        ItemStack out = PolymerItemUtils.createItemStack(itemStack, player);
        out.addEnchantment(Enchantments.BINDING_CURSE, 0);
        return out;
    }

}
