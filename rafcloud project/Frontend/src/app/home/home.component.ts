import { Component, OnInit } from '@angular/core';
import { Machine } from '../_models/machine';
import { MachineService } from '../_services/machine.service';
import { first } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  constructor() { }

  ngOnInit() {

  }

  
}
