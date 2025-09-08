import { Ccaa, Municipality, Province } from 'src/app/core/models/Municipality';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class MunicipalitiesGroup {
  private API = environment.API_URL;

  constructor(private http: HttpClient) {}

  getAllMunicipalities() {
    let url = `${this.API}/api/address/municipalities`;

    return this.http.get<Municipality[]>(url);
  }

  getAllProvinces() {
    let url = `${this.API}/api/address/provinces`;

    return this.http.get<Province[]>(url);
  }

  getAllCCAAs() {
    let url = `${this.API}/api/address/CCAAs`;
    return this.http.get<Ccaa[]>(url);
  }
}
