package net.hyper_pigeon.bottled_allays;

import net.fabricmc.api.ModInitializer;
import net.hyper_pigeon.bottled_allays.item.BottledAllayItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BottledAllays implements ModInitializer {

    public static final BottledAllayItem BOTTLE_OF_ALLAY = new BottledAllayItem(new Item.Settings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("bottled_allays", "bottle_of_allay"), BOTTLE_OF_ALLAY);
    }

}
