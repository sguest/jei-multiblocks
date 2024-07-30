# Just Enough Immersive Multiblocks

Simple Immersive Engineering addon that adds JEI support for multiblocks.

Multiblocks show up in JEI search

![JEI Search](readme-images/jei-search.png)

Multiblock recipes show basic structure and ingredients

![Arc Furnace](readme-images/arc-furnace.png)

## Specific version notes

### 1.20.1

- For Minecraft 1.20.1, Immersive Engineering switched to NeoForge loader but JEI did not. The CurseForge client will not add Forge mods to a NeoForged instance or vice versa, despite both versions being effectively identical. To use this mod in 1.20.1, you must create a Forge instance and add IE manually by downloading the mod and dragging it to the mods folder. CurseForge will display a warning saying the version is incompatible, but from testing it seems to work fine.
- Huge thanks to [FLORIAN4600](https://github.com/FLORIAN4600) for overcoming some nasty lifecycle issues and allowing this mod to live on into 1.20+ of Minecraft

### 1.16

- This mod has known issues in 1.16 and should be considered single-player-only

### Anything older than 1.16

- Backports to older versions will not happen. Please do not ask unless you personally are prepared to do the port and maintain it.
- Independent backport to 1.12.2 exists as [Rather Enough Immersive Multiblocks](https://legacy.curseforge.com/minecraft/mc-mods/rather-enough-immersive-multiblocks)
