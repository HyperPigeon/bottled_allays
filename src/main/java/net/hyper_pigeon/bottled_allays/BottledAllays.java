package net.hyper_pigeon.bottled_allays;

import net.fabricmc.api.ModInitializer;
import net.hyper_pigeon.bottled_allays.item.BottledAllayItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class BottledAllays implements ModInitializer {

    public static final BottledAllayItem BOTTLE_OF_ALLAY = new BottledAllayItem(new Item.Settings().maxCount(1));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("bottled_allays", "bottle_of_allay"), BOTTLE_OF_ALLAY);
    }

}
