package control;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import control.ImageGenerationCommand;
import control.ImageManipulationCommand;
import control.commands.FilterEnum;
import control.commands.Generation.CheckeredBoard;
import control.commands.Generation.FranceFlag;
import control.commands.Generation.GreeceFlag;
import control.commands.Generation.HorizontalRainbow;
import control.commands.Generation.Switzerland;
import control.commands.Generation.VerticalRainbow;
import control.commands.Manipulation.Dither;
import control.commands.Manipulation.Filter;
import control.commands.Manipulation.Mosaic;
import control.commands.Manipulation.Transform;

public class ControllerConstants {
  static final Map<String, Function<Scanner, ImageManipulationCommand>>
          manipulationKnownCommands = new HashMap<>();
  static final Map<String, Function<Scanner,
          ImageGenerationCommand>> generationKnownCommands = new HashMap<>();

  static {
    generationKnownCommands.put("Switzerland", scanner -> new Switzerland(scanner.nextInt()));
    generationKnownCommands.put("France", scanner -> new FranceFlag(scanner.nextInt()));
    generationKnownCommands.put("Horizontal Rainbow", scanner -> new HorizontalRainbow(
            scanner.nextInt(), scanner.nextInt()));
    generationKnownCommands.put("Vertical Rainbow", scanner -> new
            VerticalRainbow(scanner.nextInt(), scanner.nextInt()));
    generationKnownCommands.put("CheckeredBoard", scanner ->
            new CheckeredBoard(scanner.nextInt()));
    generationKnownCommands.put("Greece", scanner ->
            new GreeceFlag(scanner.nextInt(), scanner.nextInt()));

    manipulationKnownCommands.put("mosaic", scanner -> new Mosaic(scanner.nextInt()));
    manipulationKnownCommands.put("dither", scanner -> new Dither());
    manipulationKnownCommands.put("sepia", scanner -> new Transform(FilterEnum.Sepia));
    manipulationKnownCommands.put("greyscale", scanner -> new Transform(FilterEnum.GreyScale));
    manipulationKnownCommands.put("blur", scanner -> new Filter(FilterEnum.Blur));
    manipulationKnownCommands.put("sharpen", scanner -> new Filter(FilterEnum.Sharpen));
  }
}
