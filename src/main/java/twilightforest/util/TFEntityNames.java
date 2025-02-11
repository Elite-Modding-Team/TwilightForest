package twilightforest.util;

import net.minecraft.util.ResourceLocation;
import twilightforest.TwilightForestMod;

public class TFEntityNames {
	// Passive Animals
	public static final ResourceLocation WILD_BOAR = prefix("wild_boar");
	public static final ResourceLocation BIGHORN_SHEEP = prefix("bighorn_sheep");
	public static final ResourceLocation DEER = prefix("deer");
	public static final ResourceLocation PENGUIN = prefix("penguin");
	public static final ResourceLocation TINY_BIRD = prefix("tiny_bird");
	public static final ResourceLocation SQUIRREL = prefix("squirrel");
	public static final ResourceLocation BUNNY = prefix("bunny");
	public static final ResourceLocation RAVEN = prefix("raven");
	public static final ResourceLocation FIREFLY = prefix("firefly");

	public static final ResourceLocation QUEST_RAM = prefix("quest_ram");

	// Common Enemies
	public static final ResourceLocation SKELETON_DRUID = prefix("skeleton_druid");
	public static final ResourceLocation WRAITH = prefix("wraith");
	public static final ResourceLocation KOBOLD = prefix("kobold");
	public static final ResourceLocation FINAL_KOBOLD = prefix("final_kobold");
	public static final ResourceLocation HOSTILE_WOLF = prefix("hostile_wolf");

	public static final ResourceLocation REDCAP = prefix("redcap");
	public static final ResourceLocation REDCAP_SAPPER = prefix("redcap_sapper");

	public static final ResourceLocation SWARM_SPIDER = prefix("swarm_spider");
	public static final ResourceLocation HEDGE_SPIDER = prefix("hedge_spider");

	public static final ResourceLocation FIRE_BEETLE = prefix("fire_beetle");
	public static final ResourceLocation SLIME_BEETLE = prefix("slime_beetle");
	public static final ResourceLocation PINCH_BEETLE = prefix("pinch_beetle");

	public static final ResourceLocation BOGGARD = prefix("boggard"); // <- ??

	// Graveyard
	public static final ResourceLocation RISING_ZOMBIE = prefix("rising_zombie");

	// Courtyard
	public static final ResourceLocation NAGA = prefix("naga");

	// Lich Tower
	public static final ResourceLocation LICH = prefix("lich");
	public static final ResourceLocation LICH_MINION = prefix("lich_minion");
	public static final ResourceLocation LOYAL_ZOMBIE = prefix("loyal_zombie");
	public static final ResourceLocation DEATH_TOME = prefix("death_tome");

	// Swamp
	public static final ResourceLocation MOSQUITO_SWARM = prefix("mosquito_swarm");
	public static final ResourceLocation HYDRA = prefix("hydra");

	public static final ResourceLocation MINOTAUR = prefix("minotaur");
	public static final ResourceLocation MINOSHROOM = prefix("minoshroom");
	public static final ResourceLocation MAZE_SLIME = prefix("maze_slime");

	// Dark Forest
	public static final ResourceLocation MIST_WOLF = prefix("mist_wolf");
	public static final ResourceLocation KING_SPIDER = prefix("king_spider");

	public static final ResourceLocation MINI_GHAST = prefix("mini_ghast");
	public static final ResourceLocation TOWER_GHAST = prefix("tower_ghast");
	public static final ResourceLocation TOWER_GOLEM = prefix("tower_golem");
	public static final ResourceLocation TOWER_TERMITE = prefix("tower_termite");
	public static final ResourceLocation TOWER_BROODLING = prefix("tower_broodling");
	public static final ResourceLocation UR_GHAST = prefix("ur_ghast");

	public static final ResourceLocation BLOCKCHAIN_GOBLIN = prefix("blockchain_goblin");
	public static final ResourceLocation GOBLIN_KNIGHT_UPPER = prefix("goblin_knight_upper");
	public static final ResourceLocation GOBLIN_KNIGHT_LOWER = prefix("goblin_knight_lower");
	public static final ResourceLocation HELMET_CRAB = prefix("helmet_crab");
	public static final ResourceLocation KNIGHT_PHANTOM = prefix("knight_phantom");

	// Snow Region
	public static final ResourceLocation YETI = prefix("yeti");
	public static final ResourceLocation YETI_ALPHA = prefix("yeti_alpha");
	public static final ResourceLocation WINTER_WOLF = prefix("winter_wolf");

	public static final ResourceLocation SNOW_GUARDIAN = prefix("snow_guardian");
	public static final ResourceLocation STABLE_ICE_CORE = prefix("stable_ice_core");
	public static final ResourceLocation UNSTABLE_ICE_CORE = prefix("unstable_ice_core");
	public static final ResourceLocation SNOW_QUEEN = prefix("snow_queen");
	public static final ResourceLocation ICE_CRYSTAL = prefix("ice_crystal");

	// Highlands
	public static final ResourceLocation TROLL = prefix("troll");

	public static final ResourceLocation GIANT_MINER = prefix("giant_miner");
	public static final ResourceLocation ARMORED_GIANT = prefix("armored_giant");

	// Final Castle
	public static final ResourceLocation HARBINGER_CUBE = prefix("harbinger_cube");
	public static final ResourceLocation ADHERENT = prefix("adherent");
	public static final ResourceLocation CASTLE_WARRIOR = prefix("castle_warrior");
	public static final ResourceLocation CASTLE_KNIGHT = prefix("castle_knight");
	public static final ResourceLocation CASTLE_MARKSMAN = prefix("castle_marksman");
	public static final ResourceLocation CASTLE_PHANTOM = prefix("castle_phantom");
	public static final ResourceLocation CASTLE_GIANT = prefix("castle_giant");

	// Technical
	public static final ResourceLocation HYDRA_HEAD = prefix("hydra_head");
	public static final ResourceLocation CHARM_EFFECT = prefix("charm_effect");
	public static final ResourceLocation FALLING_ICE = prefix("falling_ice");
	public static final ResourceLocation CHAIN_BLOCK = prefix("chain_block");
	public static final ResourceLocation CUBE_OF_ANNIHILATION = prefix("cube_of_annihilation");

	// Projectiles
	public static final ResourceLocation ADHERENT_BOLT = prefix("adherent_bolt");
	public static final ResourceLocation NATURE_BOLT = prefix("nature_bolt");
	public static final ResourceLocation LICH_BOLT = prefix("lich_bolt");
	public static final ResourceLocation WAND_BOLT = prefix("wand_bolt");
	public static final ResourceLocation TOME_BOLT = prefix("tome_bolt");
	public static final ResourceLocation HYDRA_MORTAR = prefix("hydra_mortar");
	public static final ResourceLocation LICH_BOMB = prefix("lich_bomb");
	public static final ResourceLocation MOONWORM_SHOT = prefix("moonworm_shot");
	public static final ResourceLocation SLIME_BLOB = prefix("slime_blob");
	public static final ResourceLocation THROWN_WEP = prefix("thrown_wep");
	public static final ResourceLocation THROWN_ICE = prefix("thrown_ice");
	public static final ResourceLocation SEEKER_ARROW = prefix("seeker_arrow");
	public static final ResourceLocation ICE_ARROW = prefix("ice_arrow");
	public static final ResourceLocation ICE_SNOWBALL = prefix("ice_snowball");
	
	// Unused
	public static final ResourceLocation ROVING_CUBE = prefix("roving_cube");
	public static final ResourceLocation SLIDER = prefix("slider");
	public static final ResourceLocation CASTLE_GUARDIAN = prefix("castle_guardian");

	private static ResourceLocation prefix(String path) {
		return new ResourceLocation(TwilightForestMod.ID, path);
	}
}
