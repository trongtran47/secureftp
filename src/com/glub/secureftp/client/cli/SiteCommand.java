
//*****************************************************************************
//*
//* (c) Copyright 2002. Glub Tech, Incorporated. All Rights Reserved.
//*
//* $Id: SiteCommand.java 37 2009-05-11 22:46:15Z gary $
//*
//*****************************************************************************

package com.glub.secureftp.client.cli;

import com.glub.secureftp.bean.*;
import com.glub.secureftp.client.framework.*;

import java.io.PrintStream;
import java.util.*;

public class SiteCommand extends NetworkCommand {
  public SiteCommand() {
    super("site", CommandID.SITE_COMMAND_ID, 1, 9999, 
          "site-parameters", "send site specific command");
  }

  public SecureFTPError doIt() throws CommandException {
    SecureFTPError result = super.doIt();

    FTPSession session = SecureFTP.getFTPSession();
    PrintStream out = session.getPrintStream();

    ArrayList args = getArgs();

    try {
      StringBuffer rawCommands = new StringBuffer();
      rawCommands.append( "SITE " );
      for ( int i = 0; i < args.size(); i++ ) {
        rawCommands.append( (String)args.get(i) );
        rawCommands.append( " " );
      }
      session.getFTPBean().raw( rawCommands.toString().trim() );
    }
    catch ( FTPException fe ) {
      out.println( fe.getMessage() );
    } 

    return result;
  }
}

