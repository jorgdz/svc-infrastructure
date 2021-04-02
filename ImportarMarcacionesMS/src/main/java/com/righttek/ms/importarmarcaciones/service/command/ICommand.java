package com.righttek.ms.importarmarcaciones.service.command;

/**
 * 
 * @author JORGE
 *
 */
public interface ICommand {

	Object execute (IParam param);
	
	void undo ();
}
