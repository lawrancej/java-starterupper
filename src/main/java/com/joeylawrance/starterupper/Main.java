package com.joeylawrance.starterupper;

import com.joeylawrance.starterupper.controller.GitConfigController;
import com.joeylawrance.starterupper.controller.HostController;
import com.joeylawrance.starterupper.controller.RepositoryController;
import com.joeylawrance.starterupper.gui.GitConfigPanel;
import com.joeylawrance.starterupper.gui.HostPanel;
import com.joeylawrance.starterupper.gui.PicturePanel;
import com.joeylawrance.starterupper.gui.RepositoryPanel;
import com.joeylawrance.starterupper.gui.Wizard;
import com.joeylawrance.starterupper.model.GitConfig;
import com.joeylawrance.starterupper.model.host.impl.Bitbucket;
import com.joeylawrance.starterupper.model.host.impl.GitHub;
import com.joeylawrance.starterupper.model.host.impl.GitLab;
import com.joeylawrance.starterupper.model.host.impl.Gravatar;

public class Main {
    public static void main(String[] args) {
    	// Create the Wizard UI
    	Wizard w = new Wizard();
    	
    	GitConfig config = new GitConfig();
    	GitConfigPanel configPanel = new GitConfigPanel();
    	new GitConfigController(configPanel, config);
    	Gravatar gravatar = new Gravatar();

		w.addStep(configPanel);
		w.addStep(new HostController(new HostPanel(), gravatar).getPanel());
		w.addStep(new PicturePanel(gravatar));
		w.addStep(new HostController(new HostPanel(), new Bitbucket()).getPanel());
		w.addStep(new HostController(new HostPanel(), new GitHub()).getPanel());
		w.addStep(new HostController(new HostPanel(), new GitLab()).getPanel());
		RepositoryPanel repo = new RepositoryPanel();
		w.addStep(repo);
		w.addActionListener(new RepositoryController(repo));
		
		// Tell all the hosts about the git configuration.
		config.postConfiguration();

		// Show
    	w.setVisible(true);
    }
}
