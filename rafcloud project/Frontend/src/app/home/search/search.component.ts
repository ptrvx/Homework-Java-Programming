import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { MachineService } from 'src/app/_services/machine.service';
import { Machine } from 'src/app/_models/machine';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {
  loading = false;
  machines: Machine[];
  submitted = false;
  error = '';
  searchForm: FormGroup;

  constructor(
    private machineService: MachineService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.initialize();
    
    this.searchForm = this.formBuilder.group({
      name: ['', ],
      dateFrom: ['', Validators.pattern('([0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01]))?')],
      dateTo: ['', Validators.pattern('([0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01]))?')],
      stopped: [false],
      running: [false]
    });

  }

  get fs() { return this.searchForm.controls; }

  initialize() {
    this.loading = true;
    this.machineService.getAll().pipe(first()).subscribe(machines => {
      this.loading = false;
      this.machines = machines;
    }, error => {
      this.error = error;
      this.loading = false;
    });
  }

  onSearch() {
    this.loading = true;
    this.submitted = true;

    if (this.searchForm.invalid) {
      this.loading = false;
      return;
    }

    this.machineService.search(this.fs.name.value, this.fs.stopped.value, this.fs.running.value, this.fs.dateFrom.value, this.fs.dateTo.value)
      .pipe(first())
      .subscribe(
        machines => {
          this.loading = false;
          this.machines = machines;
          this.submitted = false;
        }, error => {
          this.error = error;
          this.loading = false;
          this.submitted = false;
        }
      );

  }

  start(id: number) {
    this.machineService.start(id).pipe(first()).subscribe();
  }

  stop(id: number) {
    this.machineService.stop(id).pipe(first()).subscribe();
  }

  restart(id: number) {
    this.machineService.restart(id).pipe(first()).subscribe();
  }

  destroy(id: number) {
    this.machineService.destroy(id.toString()).pipe(first()).subscribe(
      data => {
        this.onSearch();
      }, error => {
        this.onSearch();
      }
    );
  }


}
