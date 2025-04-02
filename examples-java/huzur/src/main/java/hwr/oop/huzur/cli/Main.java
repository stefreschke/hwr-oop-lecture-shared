package hwr.oop.huzur.cli;

import hwr.oop.huzur.application.GameStateQueryHandler;
import hwr.oop.huzur.application.NewGameService;
import hwr.oop.huzur.application.PickupStackOnGameService;
import hwr.oop.huzur.application.PlayOnGameService;
import hwr.oop.huzur.persistence.CsvFilePersistenceAdapter;
import java.util.Arrays;

public final class Main {

  @SuppressWarnings("java:S106")
  public static void main(String[] args) {
    final Cli cli = new Cli(
        System.out,
        System.err,
        NewGameService::new,
        PlayOnGameService::new,
        PickupStackOnGameService::new,
        GameStateQueryHandler::new,
        CsvFilePersistenceAdapter::new
    );
    cli.handle(Arrays.asList(args));
  }

  public static void varargMain(String... args) {
    main(args);
  }

}
