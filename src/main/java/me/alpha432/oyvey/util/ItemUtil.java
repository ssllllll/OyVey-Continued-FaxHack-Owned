package me.alpha432.oyvey.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.*;

public class ItemUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static int getSlotFromInventory(final Item item) {
        if (item == null) {
            return -1;
        }

        int slot = -1;

        for (int i = 44; i >= 0; --i) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == item) {
                if (i < 9) {
                    i += 36;
                }
                slot = i;
                break;
            }
        }

        return slot;
    }

    public static int getItemFromHotbar(final Item item) {
        int slot = -1;

        for (int i = 8; i >= 0; --i) {
            if (mc.player.inventory.getStackInSlot(i).getItem() == item) {
                slot = i;
                break;
            }
        }

        return slot;
    }

    public static int getBlockFromHotbar(final Block block) {
        int slot = -1;

        for (int i = 8; i >= 0; --i) {
            if (Block.getBlockFromItem(mc.player.inventory.getStackInSlot(i).getItem()) == block) {
                slot = i;
                break;
            }
        }

        return slot;
    }


    public static int getItemCount(final Item item) {
        int count = 0;

        for (int i = 0; i < 45; ++i) {
            final ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                count += stack.getCount();
            }
        }

        return count;
    }

    public static void replaceOffhand(final int slot) {
        mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
    }

    public static int getItemDamage(ItemStack stack) {
        return stack.getMaxDamage() - stack.getItemDamage();
    }

    public static float getDamageInPercent(ItemStack stack) {
        return (getItemDamage(stack) / (float)stack.getMaxDamage()) * 100;
    }

    public static int getRoundedDamage(ItemStack stack) {
        return (int)getDamageInPercent(stack);
    }

    public static boolean hasDurability(ItemStack stack) {
        final Item item = stack.getItem();
        return item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemShield;
    }

}