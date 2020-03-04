import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { first } from 'rxjs/operators';
import { MachineService } from 'src/app/_services/machine.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-create',
  templateUrl: './create.component.html'
})
export class CreateComponent implements OnInit {
  loading = false;
  submitted = false;
  error = '';
  createForm: FormGroup;
  @Output() insertEvent: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(
    private machineService: MachineService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.createForm = this.formBuilder.group({
      name: ['', Validators.required]
    });

  }

  get fc() { return this.createForm.controls; }

  onCreate() {
    this.submitted = true;

    if (this.createForm.invalid) {
      return;
    }

    this.loading = true;

    this.machineService.create(this.fc.name.value)
      .pipe(first())
      .subscribe(
        data => {
          this.insertEvent.emit(true);
          this.loading = false;
          this.submitted = false;
          this.createForm.reset();
        },
        error => {
          this.error = error;
          this.loading = false;
        }
      );
    
  }

}
