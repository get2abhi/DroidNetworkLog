/* (C) 2012 Pragmatic Software
   This Source Code Form is subject to the terms of the Mozilla Public
   License, v. 2.0. If a copy of the MPL was not distributed with this
   file, You can obtain one at http://mozilla.org/MPL/2.0/
 */

package io.github.bunnyblue.networklog;

public class LogEntry {
  public int uid;
  public  String uidString;
  public String in;
  public String out;
  public String proto;
  public String src;
  public String dst;
  public int len;
  public int spt;
  public int dpt;
  public long timestamp;
  public boolean validated;
  public boolean valid;

  public boolean isValid() {
    if(validated) {
      return valid;
    }

    validated = true;
    if(StringUtils.contains(in, "{}:=")) {
      valid = false;
      return false;
    }

    if(StringUtils.contains(out, "{}:=")) {
      valid = false;
      return false;
    }

    if(StringUtils.contains(proto, "{}:=")) {
      valid = false;
      return false;
    }

    if(StringUtils.contains(src, "{}:=")) {
      valid = false;
      return false;
    }

    if(StringUtils.contains(dst, "{}:=")) {
      valid = false;
      return false;
    }

    valid = true;
    return true;
  }
}
