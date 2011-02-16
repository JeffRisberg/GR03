package com.incra

import com.incra.controller.SecureController 

/**
 * The <i>ProfileController</i> class implements operations on Profiles
 *
 * @author Jeffrey Risberg
 * @since 09/20/10
 */
class ProfileController extends SecureController {
  
  def scaffold = true
  
  def index = { redirect(action: list) }
}
