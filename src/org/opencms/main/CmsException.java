/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/main/CmsException.java,v $
 * Date   : $Date: 2005/01/25 09:34:35 $
 * Version: $Revision: 1.12 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2002 - 2003 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.main;

import java.io.*;
import java.util.*;

/**
 * Master exception type for all exceptions caused in OpenCms.<p>
 * 
 * @author Alexander Kandzior (a.kandzior@alkacon.com)
 * @author Michael Emmerich (m.emmerich@alkacon.com)
 * @author Michael Moossen (m.moossen@alkacon.com)
 * 
 * @version $Revision: 1.12 $
 */
public class CmsException extends Exception {

    /** Error code for bad name exception. */
    public static final int C_BAD_NAME = 3;

    /** Error code for ClassLoader errors. */
    public static final int C_CLASSLOADER_ERROR = 29;


    /**
     * This array provides descriptions for the error codes stored as
     * constants in the CmsExeption class.
     */
    public static final String[] C_ERROR_DESCRIPTION =
        {
  /*  0 */  "Unknown exception",
  /*  1 */  "Access denied",
  /*  2 */  "(code 5: moved to CmsVfsResourceNotFoundException)",
  /*  3 */  "Bad name",
  /*  4 */  "(code 4: moved to and extended by CmsDataAccessException)",  
  /*  5 */  "(code 5: moved to CmsVfsException-C_VFS_FOLDER_NOT_EMPTY)",         
  /*  6 */  "Admin access required",
  /*  7 */  "(code 7: moved to CmsSerializationException)", 
  /*  8 */  "Unknown User Group",
  /*  9 */  "Group not empty",
  /* 10 */  "Unknown User",
  /* 11 */  "No removal from Default Group",
  /* 12 */  "(code 12: moved to CmsVfsException-C_VFS_RESOURCE_ALREADY_EXISTS)",
  /* 13 */  "File not found exception",
  /* 14 */  "Filesystem exception",
  /* 15 */  "Internal use only",
  /* 16 */  "Deprecated exception: File-property is mandatory",
  /* 17 */  "Service unavailable",
  /* 18 */  "Unknown XML datablock",
  /* 19 */  "Corrupt internal structure",
  /* 20 */  "Wrong XML content type",
  /* 21 */  "XML parsing error",
  /* 22 */  "Could not process OpenCms special XML tag",
  /* 23 */  "Could not call user method",
  /* 24 */  "Could not call process method",
  /* 25 */  "XML tag missing",
  /* 26 */  "Wrong XML template class",
  /* 27 */  "No XML template class",
  /* 28 */  "(code 28: moved to CmsLoaderException)",
  /* 29 */  "OpenCms class loader error",
  /* 30 */  "New password is too short",
  /* 31 */  "(code 31: unused)",
  /* 32 */  "(code 32: moved to CmsVfsException-C_VFS_RESOURCE_DELETED)",
  /* 33 */  "DriverManager init error",
  /* 34 */  "Registry error",
  /* 35 */  "Security Manager initialization error",
  /* 36 */  "(code 36: unused)",
  /* 37 */  "Wrong scheme for http resource",
  /* 38 */  "Wrong scheme for https resource",
  /* 39 */  "Error in Flex cache",
  /* 40 */  "Error in Flex loader",
  /* 41 */  "Group already exists",
  /* 42 */  "User already exists",
  /* 43 */  "Import error",
  /* 44 */  "Export error"
        };

    /** 
     * Error code for file exists exception.<p>
     * 
     * @deprecated use a <code>{@link org.opencms.file.CmsVfsException}</code> instead
     */    
    public static final int C_FILE_EXISTS = 12;

    /** Error code for file not found exception. */    
    public static final int C_FILE_NOT_FOUND = 13;

    /** Error code filesystem error. */
    public static final int C_FILESYSTEM_ERROR = 14;

    /** Error code for Flex cache. */
    public static final int C_FLEX_CACHE = 39;

    /** Error code for Flex loader. */
    public static final int C_FLEX_LOADER = 40;

    /** Error code for group not empty exception. */
    public static final int C_GROUP_NOT_EMPTY = 9;

    /** Error code for HTTP streaming error. */
    public static final int C_HTTPS_PAGE_ERROR = 37;

    /** Error code for HTTPS streaming error. */
    public static final int C_HTTPS_REQUEST_ERROR = 38;

    /** Error code internal file. */
    public static final int C_INTERNAL_FILE = 15;
    
    /** Error code that a group to be created already exists. */
    public static final int C_GROUP_ALREADY_EXISTS = 41;
    
    /** Error code that a user to be created already exists. */
    public static final int C_USER_ALREADY_EXISTS = 42;    

    /** 
     * Error code for access denied exception for vfs resources.
     * @deprecated use a <code>{@link org.opencms.security.CmsSecurityException}</code> instead
     */    
    public static final int C_NO_ACCESS = 1;

    /** Error code for no default group exception. */
    public static final int C_NO_DEFAULT_GROUP = 11;

    /** Error code for no group exception. */
    public static final int C_NO_GROUP = 8;

    /** Error code for no user exception. */
    public static final int C_NO_USER = 10;

    /** Error code for no admin exception. */
    public static final int C_NOT_ADMIN = 6;

    /** 
     * Error code for not empty exception.<p>
     * 
     * @deprecated use a <code>{@link org.opencms.file.CmsVfsException}</code> instead
     */    
    public static final int C_NOT_EMPTY = 5;

    /** 
     * Error code for not found exception.<p>
     * 
     * @deprecated use a <code>{@link org.opencms.db.CmsObjectNotFoundException}</code> 
     *    or <code>{@link org.opencms.file.CmsVfsResourceNotFoundException}</code> instead
     */    
    public static final int C_NOT_FOUND = 2;

    /** Error code for driver manager initialization errors. */
    public static final int C_RB_INIT_ERROR = 33;

    /** Error code for Registry exception. */
    public static final int C_REGISTRY_ERROR = 34;

    /** Error code for security manager initialization error. */
    public static final int C_SM_INIT_ERROR = 35;

    /** 
     * Error code for accessing a deleted resource.<p>
     * 
     * @deprecated use a <code>{@link org.opencms.file.CmsVfsException}</code> instead
     */    
    public static final int C_RESOURCE_DELETED = 32;

    /** 
     * Error code for serialization exception. 
     * 
     * @deprecated use a <code>{@link org.opencms.db.CmsSerializationException}</code> instead
     */
    public static final int C_SERIALIZATION = 7;

    /** Error code service unavailable. */
    public static final int C_SERVICE_UNAVAILABLE = 17;

    /** 
     * Error code for sql exception.<p>
     * 
     * @deprecated use a <code>{@link org.opencms.db.CmsDataAccessException}</code> 
     *      or one of their subclasses instead
     */    
    public static final int C_SQL_ERROR = 4;

    /** 
     * Error code for unknown exception.
     *  
     * @deprecated use a <code>{@link org.opencms.db.CmsDataAccessException}</code> 
     *      or one of their subclasses instead
     */
    public static final int C_UNKNOWN_EXCEPTION = 0;

    /** Error code for corrupt internal structure. */
    public static final int C_XML_CORRUPT_INTERNAL_STRUCTURE = 19;

    /** Error code for XML process method not found. */
    public static final int C_XML_NO_PROCESS_METHOD = 24;

    /** Error code for no XML template class. */
    public static final int C_XML_NO_TEMPLATE_CLASS = 27;

    /** Error code for XML user method not found. */
    public static final int C_XML_NO_USER_METHOD = 23;

    /** Error code for XML parsing error. */
    public static final int C_XML_PARSING_ERROR = 21;

    /** Error code for XML processing error. */
    public static final int C_XML_PROCESS_ERROR = 22;

    /** Error code for missing XML tag. */
    public static final int C_XML_TAG_MISSING = 25;

    /** Error code for unknown XML datablocks. */
    public static final int C_XML_UNKNOWN_DATA = 18;

    /** Error code for wrong XML content type. */
    public static final int C_XML_WRONG_CONTENT_TYPE = 20;

    /** Error code for wrong XML template class. */
    public static final int C_XML_WRONG_TEMPLATE_CLASS = 26;

    /** Error code for import issues. */
    public static final int C_IMPORT_ERROR = 41;

    /** Error code for export issues. */
    public static final int C_EXPORT_ERROR = 42;

    /** A string message describing the CmsEception. */
    protected String m_message;

    /** Stores a forwarded exception. */
    protected Throwable m_rootCause;

    /** Stores the error code of the CmsException. */
    protected int m_type;

    /** Flag to set processing of a saved forwared root exception. */
    protected boolean m_useRootCause;

    /**
     * Constructs a simple CmsException.<p>
     */
    public CmsException() {
        this("", 0, null, false);
    }

    /**
     * Constructs a CmsException with the provided error code, 
     * the error codes used should be the constants from the CmsEception class or subclass.<p>
     *
     * @param type exception error code
     */
    public CmsException(int type) {
        this("CmsException ID: " + type, type, null, false);
    }

    /**
     * Constructs a CmsException with the provided error code and
     * a given root cause.<p>
     * 
     * The error codes used should be the constants from the CmsEception class or subclass.<p>
     *
     * @param type exception error code
     * @param rootCause root cause exception
     */
    public CmsException(int type, Throwable rootCause) {
        this("CmsException ID: " + type, type, rootCause, false);
    }

    /**
     * Constructs a CmsException with the provided description message.<p>
     *
     * @param message the description message
     */
    public CmsException(String message) {
        this(message, 0, null, false);
    }

    /**
     * Contructs a CmsException with the provided description message and error code.<p>
     * 
     * @param message the description message
     * @param type exception error code
     */
    public CmsException(String message, int type) {
        this(message, type, null, false);
    }

    /**
     * Construtcs a CmsException with the provided description message, error code and 
     * a given root cause.<p>
     *
     * @param message the description message
     * @param type exception error code
     * @param rootCause root cause exception
     */
    public CmsException(String message, int type, Throwable rootCause) {
        this(message, type, rootCause, false);
    }

    /**
     * Construtcs a CmsException with the provided description message, error code and 
     * a given root cause, 
     * the further processing of the exception can be controlled 
     * with the <code>useRoot</code> parameter.
     *
     * @param message the description message
     * @param type exception error code
     * @param rootCause root cause exception
     * @param useRoot if true, use root case for exception display  
     */
    public CmsException(String message, int type, Throwable rootCause, boolean useRoot) {
        super(CmsException.class.getName() + ": " + message);
        this.m_message = message;
        this.m_type = type;
        this.m_rootCause = rootCause;
        this.m_useRootCause = useRoot;
    }

    /**
     * Construtcs a CmsException with the provided description message and 
     * a given root cause.<p>
     *
     * @param message the description message
     * @param rootCause root cause exception
     */
    public CmsException(String message, Throwable rootCause) {
        this(message, 0, rootCause, false);
    }
    
    /**
     * Returns the description String for the provided CmsException type, subclasses of 
     * CmsException should overwrite this method for the types they define.<p>
     * 
     * @param type exception error code 
     * @return the description String for the provided CmsException type
     */
    protected String getErrorDescription(int type) {
        if (CmsException.C_ERROR_DESCRIPTION.length >= type) {
            return CmsException.C_ERROR_DESCRIPTION[type];
        } else {
            return this.getClass().getName();
        }
    }

    /**
     * Get the root cause Exception which was provided
     * when this exception was thrown.<p>
     *
     * @return the root cause Exception
     */
    public Exception getException() {
        if (m_useRootCause) {
            return null;
        }
        try {
            return (Exception)getRootCause();
        } catch (ClassCastException e) {
            return null;
        }
    }

    /**
     * Returns the exception description message.<p>
     *
     * @return the exception description message
     */
    public String getMessage() {
        return (m_message != null) ? getClass().getName() + ": " + m_message : getClass().getName();
    }

    /**
     * Returns the description message.<p>
     * 
     * @return the description message
     */
    public String getShortMessage() {
        return m_message; 
    }
    
    /**
     * Get the root cause Throwable which was provided
     * when this exception was thrown.<p>
     *
     * @return the root cause Throwable
     */
    public Throwable getRootCause() {
        return m_rootCause;
    }

    /**
     * Returns a short String describing this exception.<p>
     *
     * @return a short String describing this exception
     */
    public String getShortException() {
        return getMessage() + " [Code " + getType() + " - " + getErrorDescription(getType()) + "]";
    }

    /**
     * Return a string with the stacktrace for this exception
     * and for all encapsulated exceptions.<p>
     *
     * @return java.lang.String
     */
    public String getStackTraceAsString() {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);

        if (m_useRootCause && (m_rootCause != null)) {
            // use stack trace of root cause
            m_rootCause.printStackTrace(pw);
        } else {
            // use stack trace of this eception and add the root case 
            super.printStackTrace(pw);

            // if there are any encapsulated exceptions, write them also.
            if (m_rootCause != null) {
                StringWriter _sw = new StringWriter();
                PrintWriter _pw = new PrintWriter(_sw);
                _pw.println("-----------");
                _pw.println("Root cause:");
                m_rootCause.printStackTrace(_pw);
                _pw.close();
                try {
                    _sw.close();
                } catch (Exception exc) {

                    // ignore the exception
                }
                StringTokenizer st = new StringTokenizer(_sw.toString(), "\n");
                while (st.hasMoreElements()) {
                    String s = ">" + (String)st.nextElement();
                    while ((s != null) && (!"".equals(s)) && ((s.endsWith("\r") || s.endsWith("\n") || s.endsWith(">")))) {
                        s = s.substring(0, s.length() - 1);
                    }
                    if ((s != null) && (!"".equals(s))) {
                        pw.println(s);
                    }
                }
            }
        }
        pw.close();
        try {
            sw.close();
        } catch (Exception exc) {
            // ignore the exception
        }
        return sw.toString();
    }

    /**
     * Returns the type of the CmsException.<p>
     *
     * @return the type of the CmsException
     */
    public int getType() {
        return m_type;
    }

    /**
     * Prints the exception stack trace to System.out.<p>
     */
    public void printStackTrace() {
        printStackTrace(System.out);
    }

    /**
     * Prints this CmsException and it's stack trace to the
     * specified print stream.<p>
     * 
     * @param s the stream to print to
     */
    public void printStackTrace(java.io.PrintStream s) {
        s.println(getStackTraceAsString());
    }

    /**
     * Prints this CmsException and it's backtrace to the specified
     * print writer.<p>
     * 
     * @param s the print writer to print to
     */
    public void printStackTrace(java.io.PrintWriter s) {
        s.println(getStackTraceAsString());
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(getShortException());
        if (m_rootCause != null) {
            result.append("\nRoot cause was: ");
            result.append(m_rootCause);
        }
        return result.toString();
    }

    /**
     * Returns the stack trace of an exception as a String.<p>
     * 
     * If the exception is a CmsException, 
     * also writes the root cause to the String.<p>
     * 
     * @param e the exception to get the stack trace from
     * @return the stack trace of an exception as a String
     */
    public static String getStackTraceAsString(Throwable e) {    
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        if (e instanceof CmsException) {
            // if the exception is a CmsException, also write the root cause to the String
            CmsException cmsException = (CmsException)e;
            if (cmsException.getException() != null) {
                cmsException.getException().printStackTrace(writer);
            }
        }
        try {
            writer.close();
            stringWriter.close();
        } catch (Throwable t) {
            // ignore
        }
        return stringWriter.toString();
    }
}