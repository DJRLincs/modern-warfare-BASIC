package com.vicmatskiv.mw;

import static com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider.compatibility;

import com.vicmatskiv.mw.entities.BanditEntityFactory;
import com.vicmatskiv.mw.entities.SampleMissionGiverEntityFactory;
import com.vicmatskiv.weaponlib.ai.EntityConfiguration;
import com.vicmatskiv.weaponlib.ai.EntityCustomMob;
import com.vicmatskiv.weaponlib.ai.InfectionAttack;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBiomeType;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityAIAttackOnCollide;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class Entities {

    public static void init(CommonProxy commonProxy) {

        new BanditEntityFactory().createEntity(ModernWarfareMod.MOD_CONTEXT);
        
        new SampleMissionGiverEntityFactory().createEntity(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("zombieBlistered")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(250)
            .withEntityIdSupplier(() -> 10001)
//          .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
//          .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
//          .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
//          .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
//           .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
//          .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(1, 1, 1, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.ZombieBlistered", "zombieblistered.png")
            .withHurtSound("zombie_hurt")
            .withAmbientSound("zombie_ambient")
            .withStepSound("zombie_step")
            .withDeathSound("zombie_death")
            .withMaxSpeed(0.25F) // 0.1-0.3 is normal speed
            .withCollisionAttackDamage(15.0)
            .withAiTask(1, e -> new EntityAISwimming(e))
//          .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
//           .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
//           .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
//          .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("zombieRipper")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(150)
            .withEntityIdSupplier(() -> 10002)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(1, 1, 1, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.RipperZombie", "ripperzombie.png")
            .withHurtSound("zombie_hurt")
            .withAmbientSound("zombie_ambient")
            .withStepSound("zombie_step")
            .withDeathSound("zombie_death")
            .withMaxSpeed(0.3F) // 0.1-0.3 is normal speed
            .withCollisionAttackDamage(10.0)
            .withAiTask(1, e -> new EntityAISwimming(e))
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    //       .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("tornzombie")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(30)
            .withEntityIdSupplier(() -> 10003)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(20, 1, 5, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.TornZombie", "tornzombie.png")
            .withHurtSound("zombie_hurt")
            .withAmbientSound("zombie_ambient")
            .withStepSound("zombie_step")
            .withDeathSound("zombie_death")
            .withAiTask(1, e -> new EntityAISwimming(e))
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))

            .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("necromorph")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(200)
            .withEntityIdSupplier(() -> 10004)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(0, 0, 0, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.Necromorph", "necromorph.png")
            .withHurtSound("necromorph_hurt")
            .withAmbientSound("necromorph_ambient")
            .withStepSound("step")
            .withDeathSound("necromorph_death")
            .withMaxSpeed(0.35F) // 0.1-0.3 is normal speed
            .withCollisionAttackDamage(5.0)
            .withAiTask(1, e -> new EntityAISwimming(e))
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    //       .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("licker")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(200)
            .withEntityIdSupplier(() -> 10005)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(0, 0, 0, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.Licker", "licker.png")
            .withHurtSound("hurt")
            .withAmbientSound("drawweapon")
    //      .withStepSound("step")
    //      .withAmbientSound(sound)
            .withMaxSpeed(0.35F) // 0.1-0.3 is normal speed
            .withCollisionAttackDamage(10.0)
            .withAiTask(1, e -> new EntityAISwimming(e))
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    //       .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("zombie")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(30)
            .withEntityIdSupplier(() -> 10006)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(50, 80, 150, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.Zombie", "zombie_1.png")
            .withTexturedModelVariant("com.vicmatskiv.mw.models.Zombie", "zombie_2.png")
            .withTexturedModelVariant("com.vicmatskiv.mw.models.Zombie", "zombie_3.png")
            .withHurtSound("zombie_hurt")
            .withAmbientSound("zombie_ambient")
            .withStepSound("zombie_step")
            .withDeathSound("zombie_death")
            .withAiTask(1, e -> new EntityAISwimming(e))
            .withMaxSpeed(0.3F) // 0.1-0.3 is normal speed
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    
    //       .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("hazmat_zombie")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(40)
            .withEntityIdSupplier(() -> 10007)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(50, 80, 150, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.HazmatZombie", "hazmatzombie.png")
            .withHurtSound("hazmatzombie_hurt")
            .withAmbientSound("hazmatzombie_ambient")
            .withStepSound("zombie_step")
            .withDeathSound("hazmatzombie_death")
            .withAiTask(1, e -> new EntityAISwimming(e))
            .withMaxSpeed(0.3F) // 0.1-0.3 is normal speed
//            .withCollisionAttackDamage(30.0)
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    
    //       .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("Defiled Zombie")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(300)
            .withEntityIdSupplier(() -> 10008)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(0, 0, 0, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.DefiledZombie", "defiledzombie.png")
            .withHurtSound("zombie_hurt")
            .withAmbientSound("zombie_ambient")
            .withStepSound("zombie_step")
            .withDeathSound("zombie_death")
            .withAiTask(1, e -> new EntityAISwimming(e))
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    
//           .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
            .withName("deathclaw")
            .withBaseClass(EntityCustomMob.class)
            .withMaxHealth(5000)
            .withEntityIdSupplier(() -> 10009)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
    //      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
    //      .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
    //       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
    //      .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
            .withPrimaryEquipmentDropChance(1f)
            .withSpawn(0, 0, 0, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
            .withSpawnEgg(0xA0A000, 0xA0A010)
            .withTexturedModelVariant("com.vicmatskiv.mw.models.Deathclaw", "deathclaw.png")
            .withHurtSound("deathclaw_hurt")
            .withAmbientSound("deathclaw_ambient")
          .withStepSound("deathclaw_step")
            .withDeathSound("deathclaw_death")
            .withAiTask(1, e -> new EntityAISwimming(e))
            .withCollisionAttackDamage(30.0)
            .withMaxSpeed(0.45F) // 0.1-0.3 is normal speed
    //      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
    //       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
            .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
            .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
            .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 100.0F))
            .withAiTask(6, e -> new EntityAILookIdle(e))
            .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
            .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
            .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
    
    //       .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
    //      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
            .register(ModernWarfareMod.MOD_CONTEXT);
        
        new EntityConfiguration.Builder()
        .withName("molded")
        .withBaseClass(EntityCustomMob.class)
        .withMaxHealth(100)
        .withEntityIdSupplier(() -> 10010)
//      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 4f, Magazines.Magazine9mm)
//      .withEquipmentOption(Guns.MakarovPM, EnumDifficulty.EASY, 3.8f, Magazines.Magazine9mm, Attachments.Silencer9mm)
//       .withEquipmentOption(Guns.AK74U, EnumDifficulty.EASY, 0.1f, Magazines.AKS74UMag) //, Attachments.Silencer9mm)
//       .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag)
//    .withEquipmentOption(Guns.VSSVintorez, EnumDifficulty.EASY, 0.1f, Magazines.VSSVintorezMag, Attachments.PSO1)
//   .withEquipmentOption(Guns.Remington870, EnumDifficulty.EASY, 0.07f)
        .withPrimaryEquipmentDropChance(1f)
//        .withSpawn(1, 1, 1, CompatibleBiomeType.PLAINS, CompatibleBiomeType.FOREST, CompatibleBiomeType.HILLS)
        .withSpawnEgg(0xA0A000, 0xA0A010)
        .withTexturedModelVariant("com.vicmatskiv.mw.models.Molded", "molded.png")
        .withHurtSound("molded_hurt")
        .withAmbientSound("molded_ambient")
        .withDeathSound("molded_death")
//    .withStepSound("step")
        .withMaxSpeed(0.2F) // 0.1-0.3 is normal speed
        .withCollisionAttackDamage(10.0)
        .withAiTask(1, e -> new EntityAISwimming(e))
//      .withAiTask(3, e -> compatibility.createAiAvoidEntity((EntityCreature)e, EntityWolf.class, 6.0F, 1.0D, 1.2D))
//       .withAiTask(4, e -> new EntityAIAttackRangedWeapon((EntityCustomMob)e, 1.0D, 10, 30.0F))
        .withAiTask(3, e -> new CompatibleEntityAIAttackOnCollide((EntityCustomMob)e, EntityPlayer.class, 1.0D, false))
        .withAiTask(5, e -> new EntityAIWander((EntityCreature)e, 1.0D))
        .withAiTask(6, e -> new EntityAIWatchClosest(e, EntityPlayer.class, 50.0F))
        .withAiTask(6, e -> new EntityAILookIdle(e))
        .withAiTargetTask(1, e -> new EntityAIHurtByTarget((EntityCreature)e, false))
        .withAiTargetTask(2, e -> compatibility.createAINearestAttackableTarget(e, EntityPlayer.class, true))
        .withAiTargetTask(3, e -> compatibility.createAINearestAttackableTarget(e, EntityVillager.class, true))
//   .withCollisionAttack(new InfectionAttack(0.1f, 20000, 1.2f, 1.0f, 1.8f))
//      .withDelayedAttack(new ExplosionAttack(2f, true, 30))
        .register(ModernWarfareMod.MOD_CONTEXT);
        }

}