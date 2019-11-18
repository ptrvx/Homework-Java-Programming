import { Component, OnInit } from '@angular/core';

import { faFacebook, faLinkedin, faBattleNet, faTelegram, faInstagram, faGit, faSkype} from '@fortawesome/free-brands-svg-icons'

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  public facebook = faFacebook;
  public linkedin = faLinkedin;
  public battlenet = faBattleNet;
  public telegram = faTelegram;
  public instagram = faInstagram;
  public git = faGit;
  public skype = faSkype;

  constructor() { }

  ngOnInit() {
  }

}
