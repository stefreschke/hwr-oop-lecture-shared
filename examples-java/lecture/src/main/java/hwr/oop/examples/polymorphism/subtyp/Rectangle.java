package hwr.oop.examples.polymorphism.subtyp;

class Rectangle implements Quadrangle {

  private final double height;
  private final double width;

  public Rectangle(double height, double width) {
    this.height = height;
    this.width = width;
  }

  @Override
  public double getPerimeterLength() {
    return 2 * height + 2 * width;
  }

  @Override
  public double getAreaSize() {
    return height * width;
  }

  @Override
  public double[] getDistinctAngles() {
    return new double[]{90};
  }
}
