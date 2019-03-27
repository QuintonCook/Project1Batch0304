package force.commands;

public class UnknownCommand extends UserCommand {

	@Override
	public void process() {
		logger.info("A user tried to execute an unknown command");
	}

}
