import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  contactForm: FormGroup; 

  constructor(private fb: FormBuilder) {
    this.contactForm = this.fb.group({
      'firstName': '',
      'lastName': '',
      'email': ['', Validators.required],
      'text': ['', Validators.required]
    });
   }

  ngOnInit() {
  }


  submitForm() {
    const data = this.contactForm.value;
    console.log(data);
  }

}
