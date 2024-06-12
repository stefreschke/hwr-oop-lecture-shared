package hwr.oop.huzur;

import java.io.IOException;

public enum IOExceptionBomb {
  DO {
    @Override
    public void fire() throws IOException {
      throw new IOException(MESSAGE);
    }
  }, DONT {
    @Override
    public void fire() {
      // do nothing
    }
  };

  public static final String MESSAGE = "Here goes everything exploding...";

  public abstract void fire() throws IOException;
}
