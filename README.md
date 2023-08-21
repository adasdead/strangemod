<h1 align="center">StrangeMod (in dev)</h1>

> __Warning__: This is the LTS version of the mod for 1.16.5, which will be supported until the popularity of 1.16.5 fades.

A very strange mod for Minecraft version **1.16.5**. The project has no clear goal, we add whatever comes to our mind. Of course, sometimes we borrow some ideas from other mods and games for items/blocks, but we try to come up with a lot of things ourselves.

The mod contains various items: starting from an anonymous mask that adds a Jump Boost effect, ending with a buggy fork that you can eat your enemies with.

We try to write the best quality code possible, but because we have little experience in creating mods for Minecraft, it sometimes (or maybe everywhere xd) turns out to be terrible.

## Building

To build the mod you will need JDK 17 or higher and Gradle 7 or higher. You can download the source code of the mod from GitHub and run the following commands in the terminal:

```bash
# Clone the repository
git clone https://github.com/adasdead/strangemod

# Go to the project folder
cd strangemod

# Build the jar file of the mod
gradle build # or ./gradlew build

# Copy the jar file to the mods folder of your Minecraft
cp build/libs/strange_mod-1.0.jar ~/.minecraft/mods
```

## Usage
To use the mod you need to run Minecraft with Forge installed for version 1.16.5 and select the Forge profile in the launcher. Then you can create a new world or load an existing one and enjoy the strange items from the mod. You can find them in creative mode or craft them by recipes from [JEI](https://github.com/mezz/JustEnoughItems).

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
