package controle;

import facade.Facade;
import facade.IFacade;

public abstract class AbstractCommand implements ICommand {
	protected IFacade fachada = new Facade();
}
