/**
 * It is an abstract class that is for decorator pattern. It implements the IViewModel interface.
 * It has a view model object.
 */
public abstract class Decorator implements IViewModel {
  protected IViewModel model;

  /**
   * The constructor. It takes in a IViewModel.
   * @param model the model
   */
  public Decorator(IViewModel model) {
    this.model = model;
  }
}
