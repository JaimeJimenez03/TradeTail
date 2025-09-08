/**
 * StorageUtils (class)
 * Almacenamiento interno
 *
 * @author  Victor Espina S
 * @date    Sep 22, 2023
 *
 */
import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage-angular';
@Injectable({
  providedIn: 'root',
})
export class StorageService {
  private _storage: any;

  constructor(private storage: Storage) {
    this.initialize();
  }

  /*
        void initialize()
    
        INITIALIZES STORAGE ENGINE  
     */
  async initialize() {
    this._storage = await this.storage.create();
  } // initialize

  /*
        void store(string, object)
    
        STORES A VALUE IN THE LOCAL STORAGE
     */
  store(key: string, value: any) {
    this._storage.set(key, value);
  } // store

  /*
        object recall(string)
    
        RECALL A SAVED VALUE FROM THE LOCAL STORAGE
     */
  async recall(key: string, defaultValue?: any) {
    if (!this._storage) {
      this._storage = await this.storage.create();
    }
    const value = await this._storage.get(key);
    return value || defaultValue;
  } // recall

  /*
        void clear()
    
        REMOVES CURRENT CONTENT ON THE LOCAL STORAGE
     */
  async clear() {
    await this._storage.clear();
  } // clear

  /*
        array list()
    
        LIST CURRENT CONTENT OF LOCAL STORAGE
  async list() {
    const keys = await this._storage.keys();
    let content = {};
    keys.map((key) => (content[key] = this.recall(key)));
    return content;
  }      */
}
