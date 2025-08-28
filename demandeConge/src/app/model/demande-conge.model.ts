export interface DemandeConge {
  id: number;
  typeConge: string;
  employeNomComplet: string | null;
  managerNomComplet: string | null;
  dateDebut: string;
  dateFin: string;
  dateSoumission: string;
  dateTraitement: string | null;
  statut: string;
  motifRecu?: string | null;
}
