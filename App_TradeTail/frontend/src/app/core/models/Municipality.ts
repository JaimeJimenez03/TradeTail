export interface Municipality {
  id: number;
  cnum: number;
  dc: number;
  name: string;
  province: Province;
}

export interface Province {
  id: number;
  name: string;
  ccaa: Ccaa;
}

export interface Ccaa {
  id: number;
  name: string;
}
