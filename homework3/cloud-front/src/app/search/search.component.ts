import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service'
import { FormBuilder } from '@angular/forms';
import { Machine } from '../machine';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  searchForm;
  // private machines: Array<Machine>;
  private machines;

  constructor(private api: ApiService, private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
    name: '',
    run: '',
    stop: '',
    dateFrom: '',
    dateTo: ''
  }) }

  ngOnInit() {
    this.resetData();
  }

  resetData() {
    this.api.getMachines().subscribe(data => {
      this.machines = data;
    });
  }

  testButton() {
    console.log(this.machines);
  }

  submitSearch(value) {
    this.api.searchMachines(value.name, value.stop, value.run, value.dateTo, value.dateFrom).subscribe(data => {
      this.machines = data;
    })
  }

  start(id) {
    this.api.startMachine(id);
  }

  stop(id) {
    this.api.stopMachine(id);
  }

  restart(id) {
    this.api.restartMachine(id);
  } 

  delete(id) {
    this.api.deleteMachine(id);
  }

}
