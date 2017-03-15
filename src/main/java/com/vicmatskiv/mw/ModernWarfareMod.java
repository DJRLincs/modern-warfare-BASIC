package com.vicmatskiv.mw;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;

@Mod(modid = ModernWarfareMod.MODID, version = ModernWarfareMod.VERSION)
public class ModernWarfareMod {

    private static final String CONFIG_PROPERTY_CATEGORY_GENERAL = "general";
    private static final String CONFIG_PROPERTY_ORE_GENERATION_ENABLED = "Ore generation enabled";
    private static final String MODERN_WARFARE_CONFIG_FILE_NAME = "ModernWarfare.cfg";
    public static final String MODID = "mw";
    public static final String VERSION = "1.8.1";
    
    @SidedProxy(serverSide = "com.vicmatskiv.weaponlib.CommonModContext", clientSide = "com.vicmatskiv.weaponlib.ClientModContext")
    public static ModContext MOD_CONTEXT;
    
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        
    public static CreativeTabs gunsTab = new GunsTab(
            CreativeTabs.getNextID(), "guns_tab");
    
    public static CreativeTabs FunGunsTab = new FunGunsTab(
            CreativeTabs.getNextID(), "FunGuns_tab");

    @SidedProxy(serverSide = "com.vicmatskiv.mw.CommonProxy", 
            clientSide = "com.vicmatskiv.mw.ClientProxy")
    public static CommonProxy proxy;
    
    public static boolean oreGenerationEnabled = true;
    
    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        configure(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(this, new CompatibleFmlInitializationEvent(event));
        initRecipies(event);
    }

    private void configure(File suggestedConfig) {
        File parentDirectory = suggestedConfig.getParentFile();
        File configFile;
        if(parentDirectory != null) {
            configFile = new File(parentDirectory, MODERN_WARFARE_CONFIG_FILE_NAME);
        } else {
            configFile = new File(MODERN_WARFARE_CONFIG_FILE_NAME);
        }
        Configuration config = new Configuration(configFile);
        config.load();
        Property oreGenerationEnabled = config.get(CONFIG_PROPERTY_CATEGORY_GENERAL, CONFIG_PROPERTY_ORE_GENERATION_ENABLED, true);
        ModernWarfareMod.oreGenerationEnabled = oreGenerationEnabled.getBoolean();
        config.save();
    }

    // ItemRecipes
    //@EventHandler
    public void initRecipies(FMLInitializationEvent event) {
        
        GameRegistry.addSmelting(CommonProxy.TitaniumOre, new ItemStack(CommonProxy.TitaniumIngot), 5f);
        GameRegistry.addSmelting(CommonProxy.CopperOre, new ItemStack(CommonProxy.CopperIngot), 5f);
        GameRegistry.addSmelting(CommonProxy.LeadOre, new ItemStack(CommonProxy.LeadIngot), 5f);
        GameRegistry.addSmelting(CommonProxy.TinOre, new ItemStack(CommonProxy.TinIngot), 5f);
        GameRegistry.addSmelting(CommonProxy.BauxiteOre, new ItemStack(CommonProxy.AluminumIngot), 5f);
        GameRegistry.addSmelting(CommonProxy.SiliconOre, new ItemStack(CommonProxy.Silicon), 5f);
        GameRegistry.addSmelting(CommonProxy.TantalumOre, new ItemStack(CommonProxy.TantalumIngot), 5f);
        GameRegistry.addSmelting(CommonProxy.SulfurDust, new ItemStack(Items.GUNPOWDER), 5f);
        GameRegistry.addSmelting(CommonProxy.SteelDust, new ItemStack(CommonProxy.SteelIngot), 5f);
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Marinehelmet, 
                "AAA",
                "AGA",
                "X X",
                'A', CommonProxy.TanCloth,
                'X', "ingotTitanium"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Marinechest,
                "A A",
                "AAA",
                "XXX",
                'A', CommonProxy.TanCloth,
                'X', "ingotTitanium"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Marineboots,
                "A A",
                "A A",
                "X X",
                'A', CommonProxy.TanCloth,
                'X', "ingotTitanium"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Spetznazhelmet,
                "AAA",
                "AGA",
                "X X",
                'A', CommonProxy.GreenCloth,
                'X', "ingotTitanium"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Spetznazchest,
                "A A",
                "AAA",
                "XXX",
                'A', CommonProxy.GreenCloth,
                'X', "ingotTitanium"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Spetznazboots,
                "A A",
                "A A",
                "X X",
                'A', CommonProxy.GreenCloth,
                'X', "ingotTitanium"));
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Cloth, 3),
                "XAX",
                "AXA",
                "XAX",
                'X', Items.STRING,
                'A', Blocks.WOOL
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.TanCloth, 3),
                "XAX",
                "AXA",
                "XAX",
                'X', Blocks.SANDSTONE,
                'A', CommonProxy.Cloth
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.GreenCloth, 3),
                "XAX",
                "AXA",
                "XAX",
                'X', Blocks.LEAVES,
                'A', CommonProxy.Cloth
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Electronics),
                "TAI",
                "ACA",
                "RAD",
                'I', CommonProxy.Inductor,
                'T', CommonProxy.Transistor,
                'R', CommonProxy.Resistor,
                'D', CommonProxy.Diode,
                'C', CommonProxy.Capacitor,
                'A', CommonProxy.CopperWiring
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.CGrip),
                "AAA",
                "XBX",
                "  A",
                'A', CommonProxy.SteelPlate,
                'X', CommonProxy. SteelIngot,
                'B', Blocks.STONE_BUTTON
        );
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.OpticGlass,
                "XAX",
                "AEA",
                "XAX",
                'X', "ingotTitanium",
                'E', CommonProxy.CopperWiring,
                'A', Blocks.GLASS_PANE
                
        ));
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.MiniSteelPlate, 2),
                "XX",
                'X', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.SteelPlate, 2),
                "XXX",
                "XXX",
                'X', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.BigSteelPlate),
                "XAX",
                'X', CommonProxy.SteelPlate,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.MetalComponents),
                "XA",
                "AX",
                'X', CommonProxy.SteelPlate,
                'A', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M9SD),
                "AX",
                'X', CommonProxy.M9,
                'A', CommonProxy.Silencer9mm
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.SMAWRocket),
                "AXX",
                'X', CommonProxy.SteelPlate,
                'A', Blocks.TNT
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Bullets, 32),
                " R ",
                "XAX",
                "XAX",
                'X', CommonProxy.CopperIngot,
                'R', CommonProxy.LeadIngot,
                'A', Items.GUNPOWDER
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AK12),
                "XA ",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AK47),
                "XA ",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WoodWeaponStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AK74U),
                "XA ",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.SMGReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.SecondaryStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AKM),
                "XR ",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'R', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M14),
                "XA ",
                "AEA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'R', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AR15),
                "XAT",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.UnderReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.SecondaryStock,
                'T', CommonProxy.WeaponReceiver
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.HK416C),
                "XAT",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.UnderReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.RetractableStock,
                'T', CommonProxy.WeaponReceiver
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AS50),
                "XA ",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.RifleReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.SecondaryStock
        );

        GameRegistry.addRecipe(new ItemStack(CommonProxy.AUG),
                "XA ",
                "QEA",
                "  F",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Dragonuv),
                "XA ",
                " EA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.RifleReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WoodRifleStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.FamasG2),
                "RA ",
                "AEA",
                " QF",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.UnderReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'R', CommonProxy.Barrel
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Famas),
                "RX ",
                "AEA",
                " QF",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'R', CommonProxy.Barrel
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.G18),
                "RA ",
                "ERA",
                " FQ",
                'R', CommonProxy.SteelPlate,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.PistolUnderReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.G21),
                "RA ",
                "ERA",
                "  Q",
                'R', CommonProxy.SteelPlate,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.PistolUnderReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.P2000),
                "RA ",
                "REA",
                "  Q",
                'R', CommonProxy.SteelPlate,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.PistolUnderReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.G36C),
                "RA ",
                "UEA",
                " QF",
                'A', CommonProxy.SteelPlate,
                'R', CommonProxy.Barrel,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.SecondaryStock,
                'U', CommonProxy.UnderReceiver
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M16A4),
                "RAT",
                " EA",
                " QF",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.UnderReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'T', CommonProxy.WeaponReceiver,
                'R', CommonProxy.Barrel
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M16A3),
                "RAT",
                "XEA",
                " QF",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.UnderReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'T', CommonProxy.WeaponReceiver,
                'R', CommonProxy.Barrel
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M4A4),
                "XA ",
                "AEA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.SecondaryStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M9),
                "RA ",
                " AA",
                "E Q",
                'A', CommonProxy.SteelPlate,
                'R', CommonProxy.SteelIngot,
                'E', CommonProxy.PistolUnderReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Magnum),
                "RA ",
                "AFA",
                " Q ",
                'A', CommonProxy.AluminumPlate,
                'R', CommonProxy.RevolverAction,
                'Q', CommonProxy.RevolverGrip,
                'F', CommonProxy.RevolverReceiver
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.MP5),
                "TA ",
                "TEA",
                " QR",
                'A', CommonProxy.SteelPlate,
                'R', CommonProxy.WeaponStock,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'T', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.MP5K),
                "RA ",
                " EA",
                " QA",
                'A', CommonProxy.SteelPlate,
                'R', CommonProxy.SteelIngot,
                'E', CommonProxy.SMGReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.MP7),
                "RA ",
                "AEA",
                " QR",
                'A', CommonProxy.SteelPlate,
                'R', CommonProxy.SteelIngot,
                'E', CommonProxy.SMGReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.PX90),
                "AA ",
                "AE ",
                " QA",
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.P90Receiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Remington870),
                "AA ",
                "YEA",
                " QR",
                'A', CommonProxy.SteelPlate,
                'Y', Blocks.PLANKS,
                'R', CommonProxy.WoodWeaponStock,
                'E', CommonProxy.ShotgunReceiver,
                'Q', CommonProxy.ShotgunGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.KSG12),
                "AA ",
                "AEA",
                " QG",
                'G', CommonProxy.WeaponStock,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.ShotgunReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Scar),
                "RA ",
                "AEA",
                " QX",
                'X', CommonProxy.WeaponStock,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.ScarReceiver,
                'Q', CommonProxy.CGrip,
                'R', CommonProxy.Barrel
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.ScarH),
                "RA ",
                "AEA",
                " QX",
                'X', CommonProxy.WeaponStock,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'R', CommonProxy.Barrel
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.XEagle),
                "XA ",
                " EA",
                " Q ",
                'X', CommonProxy.SteelPlate,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.DeagleReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.XWP),
                "XAR",
                "AEA",
                " QF",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.RifleReceiver,
                'Q', CommonProxy.CGrip,
                'F', CommonProxy.WeaponStock,
                'R', CommonProxy.SteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M107),
                "XA ",
                "AEA",
                " QR",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.RifleReceiver,
                'Q', CommonProxy.CGrip
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M249),
                "XA ",
                "AEA",
                " QR",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.LMGReceiver,
                'Q', CommonProxy.CGrip,
                'R', CommonProxy.WeaponStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.M240),
                "XA ",
                "AEA",
                "AQR",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.LMGReceiver,
                'Q', CommonProxy.CGrip,
                'R', CommonProxy.WeaponStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.FNFAL),
                "XA ",
                "FEA",
                " QR",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.WeaponReceiver,
                'Q', CommonProxy.CGrip,
                'R', CommonProxy.WeaponStock
        );
        
        /*GameRegistry.addRecipe(new ItemStack(CommonProxy.AK47Mag),
                " XX",
                "XBX",
                "ABX",
                'X', CommonProxy.MiniSteelPlate,
                'B', CommonProxy.Bullets
        );*/
        

        GameRegistry.addRecipe(new ItemStack(CommonProxy.ACOG),
                "FXA",
                "ORG",
                "AXX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass,
                'G', Blocks.GLASS_PANE,
                'F', CommonProxy.CopperWiring
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Holo2),
                "  A",
                " RO",
                "AXX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass,
                'G', Blocks.GLASS_PANE
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Holographic2),
                "  A",
                "XRO",
                "AXX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass,
                'G', Blocks.GLASS_PANE
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Reflex),
                "A  ",
                "ORX",
                "AXX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass,
                'G', Blocks.GLASS_PANE
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Kobra),
                "X  ",
                "OGX",
                "ARX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass,
                'G', Blocks.GLASS_PANE
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Scope),
                "ARR",
                "OGO",
                "XXX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass,
                'G', Blocks.GLASS_PANE
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.HP),
                "ARX",
                "ORO",
                "AXX",
                'R', CommonProxy.Electronics,
                'A', CommonProxy.MiniSteelPlate,
                'X', CommonProxy.SteelIngot,
                'O', CommonProxy.OpticGlass
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Grip),
                "X A",
                " XX",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Grip2),
                "AXX",
                " X ",
                " X ",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.StubbyGrip),
                "AXA",
                " X ",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.VGrip),
                "XAX",
                " X ",
                " X ",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Bipod),
                " X ",
                "A A",
                "X X",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Laser),
                " X ",
                "ARE",
                "AXX",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate,
                'R', CommonProxy.LaserPointer,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Laser2),
                "AXA",
                "XRE",
                "AXX",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate,
                'R', CommonProxy.LaserPointer,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Diamond),
                "A",
                'A', Items.DIAMOND
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Gold),
                "A",
                'A', Items.GOLD_INGOT
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Emerald),
                "A",
                'A', Items.EMERALD
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Sapphire),
                "A",
                'A', new ItemStack(Items.DYE, 4, 4)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Arctic),
                "AR",
                'A', new ItemStack(Items.DYE, 0, 0),
                'R', new ItemStack(Items.DYE, 15, 15)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Amber),
                "A",
                'A', new ItemStack(Items.DYE, 14, 14)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Amethyst),
                "A",
                'A', new ItemStack(Items.DYE, 5, 5)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Desert),
                "A",
                'A', Blocks.SAND
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Forest),
                "A",
                'A', Blocks.LEAVES
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Fade),
                "A",
                'A', CommonProxy.Ruby
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Voltaic),
                "AF",
                'A', new ItemStack(Items.DYE, 5, 5),
                'F', new ItemStack(Items.DYE, 8, 8)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.LightningStrike),
                "AF",
                'A', new ItemStack(Items.DYE, 5, 5),
                'F', new ItemStack(Items.DYE, 9, 9)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.ElectricSkin),
                "AF",
                'A', new ItemStack(Items.DYE, 0, 0),
                'F', new ItemStack(Items.DYE, 12, 12)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Vulcan),
                "AFR",
                'A', new ItemStack(Items.DYE, 0, 0),
                'F', new ItemStack(Items.DYE, 12, 12),
                'R', new ItemStack(Items.DYE, 15, 15)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Asiimov),
                "AFR",
                'A', new ItemStack(Items.DYE, 0, 0),
                'F', new ItemStack(Items.DYE, 14, 14),
                'R', new ItemStack(Items.DYE, 15, 15)
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.LaserPointer),
                "XXX",
                "AAR",
                "XXX",
                'X', CommonProxy.MiniSteelPlate,
                'A', CommonProxy.Ruby,
                'R', CommonProxy.Capacitor
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.WeaponStock),
                "XX ",
                " XA",
                " XA",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.WoodWeaponStock),
                "XXX",
                "XAX",
                "XXX",
                'X', Blocks.PLANKS,
                'A', CommonProxy.WeaponStock
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.WoodRifleStock),
                "XXR",
                "RXA",
                "RAA",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate,
                'R', Blocks.PLANKS
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.RetractableStock),
                "XX ",
                "XRA",
                " A ",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate,
                'R', CommonProxy.Piston
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.SecondaryStock),
                "XA ",
                "XXA",
                " A ",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Barrel, 2),
                "X  ",
                " X ",
                "  A",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.WeaponReceiver),
                "XA ",
                "AEA",
                " AX",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.MiniSteelPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.ScarReceiver),
                "RA ",
                "AEA",
                " AX",
                'X', CommonProxy.SteelPlate,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.Electronics,
                'R', CommonProxy.SteelIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.SMGReceiver),
                "XX ",
                "AEA",
                " A ",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.UnderReceiver),
                "AXE",
                " AX",
                "  A",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.RifleReceiver),
                "XX ",
                "AEX",
                " AA",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.LMGReceiver),
                "AA ",
                "AER",
                " RA",
                'R', CommonProxy.Piston,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.PistolUnderReceiver),
                "EA ",
                "XXA",
                " XA",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.RevolverAction),
                "XA ",
                "AXA",
                " AE",
                'X', CommonProxy.Barrel,
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.RevolverGrip),
                "BRX",
                "RAR",
                " RR",
                'X', CommonProxy.AluminumPlate,
                'A', CommonProxy.SteelPlate,
                'B', Blocks.STONE_BUTTON,
                'R', CommonProxy.Plastic
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.RevolverReceiver),
                "AA ",
                "AXA",
                " AE",
                'X', CommonProxy.SteelPlate,
                'A', CommonProxy.AluminumPlate,
                'R', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.P90Receiver),
                "AXX",
                "AEA",
                " AR",
                'X', CommonProxy.SteelIngot,
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics,
                'R', CommonProxy.Piston
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.DeagleReceiver),
                "AA ",
                "AEA",
                " AA",
                'A', CommonProxy.AluminumPlate,
                'E', CommonProxy.Electronics
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.ShotgunReceiver),
                "EA ",
                "ARA",
                " AA",
                'A', CommonProxy.SteelPlate,
                'E', CommonProxy.Electronics,
                'R', CommonProxy.Piston
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.ShotgunGrip),
                "BAR",
                "RAR",
                " RA",
                'A', CommonProxy.SteelPlate,
                'B', Blocks.STONE_BUTTON,
                'R', Blocks.PLANKS
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Resistor, 3),
                "A",
                "X",
                "A",
                'A', CommonProxy.SteelIngot,
                'X', Items.COAL
                
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Transistor, 3),
                "AA",
                "XX",
                "XX",
                'A', CommonProxy.SteelIngot,
                'X', CommonProxy.Silicon
                
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Capacitor, 3),
                "XX",
                "AA",
                "RR",
                'X', CommonProxy.AluminumIngot,
                'A', CommonProxy.Plastic,
                'R', CommonProxy.TantalumIngot
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Inductor, 3),
                " A ",
                "AXA",
                " A ",
                'X', CommonProxy.Plastic,
                'A', CommonProxy.CopperWiring
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Diode, 3),
                "X",
                "A",
                "X",
                'A', CommonProxy.SteelIngot,
                'X', CommonProxy.Silicon
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.CopperWiring, 10),
                "AAA",
                'A', CommonProxy.CopperIngot
    );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.Plastic, 3),
                "AX",
                'A', Items.COAL,
                'X', Items.WATER_BUCKET
        );
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Piston,
                "XA ",
                "AXX",
                " XE",
                'A', CommonProxy.SteelPlate,
                'X', "ingotSteel",
                'E', CommonProxy.Electronics
        ));
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.SteelDust, 4),
                " X ",
                "XAX",
                " X ",
                'A', Items.IRON_INGOT,
                'X', Items.COAL
        );
        
        GameRegistry.addRecipe(new ItemStack(CommonProxy.AluminumPlate, 6),
                "XX",
                'X', CommonProxy.AluminumIngot
        );
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Magazine762x39,
                "  X",
                "X X",
                "XX ",
                'X', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.PMAG762x39,
                "  R",
                "R X",
                "XR ",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.AK12Mag,
                "  R",
                "X R",
                "XR ",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.RPK74MMag,
                "  X",
                "X X",
                "RR ",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.AK74MMag,
                "  R",
                "R X",
                "RX ",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.AK74Mag,
                "  R",
                "X X",
                "RX ",
                'X', Items.IRON_INGOT,
                'R', CommonProxy.MiniSteelPlate         
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.AKS74UMag,
                "  X",
                "X R",
                "XR ",
                'X', Items.IRON_INGOT,
                'R', CommonProxy.SteelIngot     
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.NATOMag1,
                "  X",
                "X X",
                "XX ",
                'X', CommonProxy.AluminumIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.NATOMag2,
                "  X",
                "X R",
                "XR ",
                'X', CommonProxy.AluminumIngot,
                'R', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.NATOFamasMag,
                "X X",
                "  X",
                " XX",
                'X', CommonProxy.AluminumIngot,
                'R', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.NATOG36Mag,
                "  X",
                "X X",
                "RR ",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.DragunovMag,
                "X X",
                " RR",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.FALMag,
                "X R",
                "  R",
                " RR",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M110Mag,
                "X R",
                "  R",
                " XR",
                'X', Items.IRON_INGOT,
                'R', CommonProxy.AluminumIngot              
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M14DMRMag,
                " R",
                " R",
                "XX",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Glock21Mag,
                "X",
                "X",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Glock18Mag,
                "R",
                "X",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.G18Mag,
                "R",
                "X",
                "X",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.NATODrum100,
                " X ",
                "R R",
                "R R",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M9BerettaMag, 
                "X",
                "R",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.MP40Mag,
                "X",
                "X",
                "X",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.MP5KMag,
                " R",
                " X",
                "X ",
                'X', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.DeagleMag,
                "X",
                "X",
                'X', CommonProxy.AluminumIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.AS50Mag,
                "RXR",
                "  X",
                'R', CommonProxy.AluminumIngot,
                'X', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.FNP90Mag,
                "XXX",
                'X', CommonProxy.CopperIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M107BMag,
                "RAR",
                "  A",
                'R', CommonProxy.MiniSteelPlate,
                'A', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.HKMP7Mag,
                "RA",
                " A",
                "A ",
                'A', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M1CarbineMag,
                " A",
                " R",
                "A ",
                'A', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M240Mag,
                "  A",
                "A A",
                "AAA",
                'A', Items.IRON_INGOT   
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M249Mag,
                "  R",
                "A A",
                "AAA",
                'A', Items.IRON_INGOT,
                'R', CommonProxy.MiniSteelPlate 
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.MXMag,
                " R",
                " A",
                "AR",
                'A', Items.IRON_INGOT,
                'R', CommonProxy.SteelIngot 
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.M41AMag,
                " A",
                " R",
                "AA",
                'A', CommonProxy.MiniSteelPlate,
                'A', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.L115Mag,
                "AA",
                " A",
                'A', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.ColtM1911Mag,
                "R",
                "A",
                'A', CommonProxy.MiniSteelPlate,
                'R', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.HK33Mag,
                "  R",
                "R A",
                "AR ",
                'R', CommonProxy.MiniSteelPlate,
                'A', CommonProxy.SteelIngot
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Mag10mm,
                " A",
                " R",
                'A', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Magazine9mm,
                " A",
                " A",
                "RR",
                'A', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.ScarHMag,
                "RR",
                " A",
                " A",
                'A', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.AK101Mag,
            "  A",
            "A R",
            "AR ",
                'A', CommonProxy.SteelIngot,
                'R', CommonProxy.MiniSteelPlate
                
        ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.VectorMag,
                " A",
                " A",
                "AA",
                    'A', CommonProxy.SteelIngot
                    
            ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.G3Mag,
                " F",
                " A",
                "FF",
                    'A', CommonProxy.SteelIngot,
                    'F', CommonProxy.MiniSteelPlate
                    
            ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.PP19Mag,
                "AAF",
                "   ",
                "AAF",
                    'A', CommonProxy.SteelIngot,
                    'F', CommonProxy.MiniSteelPlate
                    
            ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.Glock32Mag,
                " A",
                " A",
                "AF",
                    'A', CommonProxy.SteelIngot,
                    'F', CommonProxy.MiniSteelPlate
                    
            ));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(CommonProxy.HecateIIMag,
                " FF",
                "A A",
                    'A', CommonProxy.SteelIngot,
                    'F', CommonProxy.MiniSteelPlate
                    
            ));
        
    }
}
