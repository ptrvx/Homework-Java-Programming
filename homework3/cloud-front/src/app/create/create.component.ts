import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service'
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  createForm;

  constructor(private api: ApiService, private formBuilder: FormBuilder) {
    this.createForm = this.formBuilder.group({
      name: '',
    })
  }

  ngOnInit() {
  }

  onSubmit(data) {
    this.api.createMachine(data.name).subscribe(mac => console.log(mac));
    this.createForm.reset();
  }



}
